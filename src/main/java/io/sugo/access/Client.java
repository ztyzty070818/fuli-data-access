package io.sugo.access;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import io.sugo.util.ScpUtil;
import io.sugo.util.http.MyHttpConnection;
import org.apache.commons.io.FileUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.LocatedFileStatus;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.fs.RemoteIterator;
import org.joda.time.DateTime;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by zty on 18-10-18
 */
public class Client {
	public static ScpUtil scpUtil = new ScpUtil("192.168.0.224", "root", "123456");
//	public static String dateStr = "2018-10-10";
	public static String dateStr = new DateTime().minusDays(1).toString("yyyy-MM-dd");

	public static void addAndUpdate(String name) throws Exception {
		add(name);
//		update(name);

		MyHttpConnection.postData("http://192.168.0.223:8090/druid/indexer/v1/task",
						FileUtils.readFileToString(new File(String.format("resource/task/%s-%s.json", name, "add"))));

//		MyHttpConnection.postData("http://192.168.0.223:8090/druid/indexer/v1/task",
//						FileUtils.readFileToString(new File(String.format("resource/task/%s-%s.json", name, "update"))));
	}

	public static void add(String name) throws Exception {
		String type = "add";
		String colPath = "resource/column/" + name;
		String hivePath = String.format("/user/hive/warehouse/fuli_%s/day=%s-%s", name, dateStr, type);
		String dataPath = "resource/data/" + name + "-" + type + ".csv";

		String colStr = FileUtils.readFileToString(new File(colPath));

		Configuration conf = new Configuration();
		FileSystem fs = FileSystem.get(new URI("hdfs://localhost"), conf, "qwe");

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
			System.out.println("File does not exist: " + hivePath);
			return;
		}
		RemoteIterator<LocatedFileStatus> listFiles = fs.listFiles(new Path(hivePath), true);

		int lineCount = 0;
		while (listFiles.hasNext()) {
			LocatedFileStatus next = listFiles.next();
			String fileName = next.getPath().getName();
			Path path = next.getPath();

			if (!fileName.equals("_SUCCESS")) {
				System.out.println(fileName + "---" + path.toString());

				InputStream in = fs.open(path);
				BufferedReader reader = new BufferedReader(new InputStreamReader(in));

				String str;

				while ((str = reader.readLine()) != null) {
					lineCount++;
					FileProducer.writeToLocal(dataFile, str, columnType);
				}
			}
		}
		System.out.println("Total line count:" + lineCount);
		FileProducer.finish();

		scpUtil.putFile(dataFile.getPath(), "/data1/csv");
	}

	public static void update(String name) throws Exception {
		String type = "update";
		String colPath = "resource/column/" + name;
		String hivePath = String.format("/user/hive/warehouse/fuli_%s/day=%s-%s", name, dateStr, type);
		String jsonPath = "resource/task/" + name + "-" + type + ".json";

		String colStr = FileUtils.readFileToString(new File(colPath));

		Configuration conf = new Configuration();
		FileSystem fs = FileSystem.get(new URI("hdfs://localhost"), conf, "qwe");

		ColumnType columnType = new ColumnType(name, type, colStr);
//		columnType.writeToAccessJson();

		if (!fs.exists(new Path(hivePath))) {
			System.out.println("File does not exist: " + hivePath);
			return;
		}
		RemoteIterator<LocatedFileStatus> listFiles = fs.listFiles(new Path(hivePath), true);

		List<Map<String, Object>> dataList = Lists.newArrayList();

		while (listFiles.hasNext()) {
			LocatedFileStatus next = listFiles.next();
			String fileName = next.getPath().getName();
			Path path = next.getPath();

			if (!fileName.equals("_SUCCESS")) {
				System.out.println(fileName + "---" + path.toString());

				InputStream in = fs.open(path);
				BufferedReader reader = new BufferedReader(new InputStreamReader(in));

				String str;

				while ((str = reader.readLine()) != null) {
					Map<String, Object> dataMap = Maps.newHashMap();
					String[] strs = str.replaceAll("\\\\N", "").replaceAll("null", "").split("\001", -1);

					Map<String, String> nameTypeMap = columnType.getNameTypeMap();
					List<String> nameList = Lists.newArrayList(columnType.getNameTypeMap().keySet());
					SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");

					for (int i = 0; i < strs.length; i++) {
						String columnName = nameList.get(i);
						String value = strs[i];
						if (nameTypeMap.get(columnName).equals("date")) {
							if (value.length() > 0) {
								try {
									dataMap.put(columnName, String.valueOf(format.parse(value).getTime()));
								} catch (ParseException e) {
									System.out.println(value);
								}
							} else {
								dataMap.put(columnName, 0);
							}
						} else {
							dataMap.put(columnName, value);
						}
					}

					dataList.add(dataMap);
				}
			}
		}  // end of list files

		File jsonFile = new File(jsonPath);
		JSONObject jsonObject = new JSONObject(FileUtils.readFileToString(jsonFile));
		jsonObject.put("data", new JSONArray(dataList));

		FileUtils.writeStringToFile(jsonFile, jsonObject.toString(2));
	}
}
