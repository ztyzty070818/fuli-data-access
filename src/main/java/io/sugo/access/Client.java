package io.sugo.access;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import io.sugo.util.ScpUtil;
import io.sugo.util.http.MyHttpConnection;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.LocatedFileStatus;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.fs.RemoteIterator;
import org.joda.time.DateTime;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.URI;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * Created by zty on 18-10-18
 */
public class Client {

	public static Properties properties = new Properties();
	private static final Logger logger = LoggerFactory.getLogger(Client.class);
	public static String overlordIp;

	static {
		try {
			properties.load(new FileInputStream("conf/system.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	//	public static String dateStr = "2018-10-10";
	public static String dateStr = new DateTime().minusDays(1).toString("yyyy-MM-dd");

	public static void addAndUpdate(String name, ScpUtil scpUtil) throws Exception {

		boolean createTask = Boolean.parseBoolean(properties.getProperty("create.task"));

		List<String> actionList = Lists.newArrayList(properties.getProperty("access.action").split(","));

		if (actionList.contains("add")) {
			add(name, scpUtil);
			if (createTask) {
				String result = MyHttpConnection.postData("http://" + getOverlordIp(properties.getProperty("overlord.ip").split(",")) + "/druid/indexer/v1/task",
								FileUtils.readFileToString(new File(String.format("resource/task/%s-%s.json", name, "add"))));
				logger.info("create task " + name + " add, result: \n" + result);
			}
		}

		if (actionList.contains("update") && !name.equals("cart")) {
			if (actionList.contains("add")) {
				int sleepSeconds = Integer.parseInt(properties.getProperty("wait.for.add.seconds"));
				logger.info("wait for add action: " + sleepSeconds + "s");
				Thread.sleep(sleepSeconds * 1000);
			}
			update(name, createTask);
		}
	}

	public static void add(String name, ScpUtil scpUtil) throws Exception {
		String type = "add";
		String colPath = "resource/column/" + name;
		String hivePath = String.format("/user/hive/warehouse/fuli_%s/day=%s-%s", name, dateStr, type);
		String dataPath = "resource/data/" + name + "-" + type + ".csv";

		String colStr = FileUtils.readFileToString(new File(colPath));

		Configuration conf = new Configuration();
		FileSystem fs = FileSystem.get(URI.create(properties.getProperty("hadoop.uri")), conf, properties.getProperty("hadoop.user"));

		ColumnType columnType = new ColumnType(name, type, colStr);
		columnType.writeToAccessJson();

		File dataFile = new File(dataPath);
		if (dataFile.exists()) {
			dataFile.delete();
		}
		File parentDir = dataFile.getParentFile();
		if (!parentDir.exists()) {
			parentDir.mkdirs();
		}
		dataFile.createNewFile();

		if (!fs.exists(new Path(hivePath))) {
			logger.error("File does not exist: " + hivePath);
			return;
		}
		RemoteIterator<LocatedFileStatus> listFiles = fs.listFiles(new Path(hivePath), true);

		int lineCount = 0;
		FileProducer fileProducer = new FileProducer(dataFile);
		while (listFiles.hasNext()) {
			LocatedFileStatus next = listFiles.next();
			String fileName = next.getPath().getName();
			Path path = next.getPath();

			if (!fileName.equals("_SUCCESS")) {
				logger.info(fileName + "---" + path.toString());

				InputStream in = fs.open(path);
				BufferedReader reader = new BufferedReader(new InputStreamReader(in));

				String str;

				while ((str = reader.readLine()) != null) {
					lineCount++;
					fileProducer.writeToLocal(str, columnType);
				}
			}
		}
		logger.info("Total line count:" + lineCount);
		fileProducer.finish();

		scpUtil.putFile(dataFile.getPath(), "/tmp");
//		FileUtils.copyFile(dataFile, new File("/tmp" + dataFile.getName()));
	}

	public static void update(String name, boolean createTask) throws Exception {
		String type = "update";
		String colPath = "resource/column/" + name;
		String hivePath = String.format("/user/hive/warehouse/fuli_%s/day=%s-%s", name, dateStr, type);
		String jsonPath = "resource/task/" + name + "-" + type + ".json";

		String colStr = FileUtils.readFileToString(new File(colPath));

		Configuration conf = new Configuration();
		FileSystem fs = FileSystem.get(URI.create(properties.getProperty("hadoop.uri")), conf, properties.getProperty("hadoop.user"));

		ColumnType columnType = new ColumnType(name, type, colStr);
		columnType.writeToAccessJson();

		if (!fs.exists(new Path(hivePath))) {
			logger.error("File does not exist: " + hivePath);
			return;
		}
		RemoteIterator<LocatedFileStatus> listFiles = fs.listFiles(new Path(hivePath), true);

		List<Map<String, Object>> dataList = Lists.newArrayList();

		while (listFiles.hasNext()) {
			LocatedFileStatus next = listFiles.next();
			String fileName = next.getPath().getName();
			Path path = next.getPath();

			if (!fileName.equals("_SUCCESS")) {
				logger.info(fileName + "---" + path.toString());

				InputStream in = fs.open(path);
				BufferedReader reader = new BufferedReader(new InputStreamReader(in));

				String str;

				while ((str = reader.readLine()) != null) {
					Map<String, Object> dataMap = Maps.newHashMap();
					String[] strs = str.replaceAll("\\\\N", "null").split("\001", -1);

					Map<String, String> nameTypeMap = columnType.getNameTypeMap();
					List<String> nameList = Lists.newArrayList(columnType.getNameTypeMap().keySet());
					SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");

					for (int i = 0; i < strs.length; i++) {
						String columnName = nameList.get(i);
						String value = strs[i];
						String columnTyope = nameTypeMap.get(columnName);
						if (columnTyope.equals("date")) {
							if (value.length() > 0 && !value.equals("null")) {
								try {
									dataMap.put(columnName, String.valueOf(format.parse(value).getTime()));
								} catch (ParseException e) {
									logger.error(value);
								}
							} else {
								dataMap.put(columnName, 0);
							}
						}  else if (columnTyope.equals("int") || columnTyope.equals("double") || columnTyope.equals("float")) {
							if (StringUtils.isBlank(value) || value.equals("null")) {
								dataMap.put(columnName, 0);
							} else {
								dataMap.put(columnName, value);
							}
						} else {
							dataMap.put(columnName, value);
						}
					}

					dataList.add(dataMap);
					if (dataList.size() >= Integer.parseInt(properties.getProperty("update.size"))) {
						writeUpdateDataToTaskFile(jsonPath, dataList, createTask, name);
						dataList.clear();
					}
				}
			}
		}  // end of list files
		if (dataList.size() > 0) {
			writeUpdateDataToTaskFile(jsonPath, dataList, createTask, name);
		}
	}

	public static void writeUpdateDataToTaskFile(String jsonPath, List<Map<String, Object>> dataList, boolean createTask, String name) throws IOException {
		File jsonFile = new File(jsonPath);
		JSONObject jsonObject = new JSONObject(FileUtils.readFileToString(jsonFile));
		jsonObject.put("data", new JSONArray(dataList));
		FileUtils.writeStringToFile(jsonFile, jsonObject.toString(2));

		if (createTask) {
			String result = MyHttpConnection.postData("http://" + getOverlordIp(properties.getProperty("overlord.ip").split(",")) + "/druid/indexer/v1/task",
							FileUtils.readFileToString(new File(String.format("resource/task/%s-%s.json", name, "update"))));
			logger.info("create task " + name + " update, result: \n" + result);
		}
	}

	public static String getOverlordIp(String[] ips) {
		if (StringUtils.isNotBlank(overlordIp)) return overlordIp;

		if (ips.length < 1) {
			logger.error("overlord ip is not exist in system.properties!");
			return null;
		} else {
			for (String ip : ips) {
				try {
					String url = String.format("http://%s:8090/druid/indexer/v1/leader", ip);
					String result = MyHttpConnection.getData(url).replace("http://", "");
					if (StringUtils.isNotBlank(result))
						overlordIp = result;
					return overlordIp;
				} catch (Exception e) {
					logger.error(String.format("can not get leader from ip(%s) for overlord", ip));
				}

			}
			logger.error("WTF? overlord leader is not exist!");
			return null;
		}
	}
}
