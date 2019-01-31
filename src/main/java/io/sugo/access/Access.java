package io.sugo.access;

import io.sugo.util.DruidUtil;
import io.sugo.util.ScpUtil;
import io.sugo.util.http.MyHttpConnection;
import org.apache.commons.io.FileUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.LocatedFileStatus;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.fs.RemoteIterator;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.URI;
import java.util.Properties;

/**
 * Created by zty on 19-1-19
 */
public class Access {
	public static Properties properties = new Properties();
	private static final Logger logger = LoggerFactory.getLogger(Access.class);
	private ScpUtil scpUtil;

	public Access(ScpUtil scpUtil) {
		this.scpUtil = scpUtil;
	}

	static {
		try {
			properties.load(new FileInputStream("conf/access.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void accessNewData() throws IOException, InterruptedException {
		String dateStr = properties.getProperty("date.str", new DateTime().minusDays(1).toString("yyyy-MM-dd"));
		String hivePath = String.format("/user/hive/warehouse/access_record/day=%s", dateStr);

		Configuration conf = new Configuration();
		FileSystem fs = FileSystem
						.get(URI.create(properties.getProperty("hadoop.uri")), conf, properties.getProperty("hadoop.user"));

		if (!fs.exists(new Path(hivePath))) {
			logger.error("File does not exist: " + hivePath);
			return;
		}

		File dataFile = new File(properties.getProperty("data.file.path", "resource/data/access_record.csv"));
		if (dataFile.exists()) {
			dataFile.delete();
		}
		File parentDir = dataFile.getParentFile();
		if (!parentDir.exists()) {
			parentDir.mkdirs();
		}
		dataFile.createNewFile();
		FileProducer fileProducer = new FileProducer(dataFile, properties);

		RemoteIterator<LocatedFileStatus> listFiles = fs.listFiles(new Path(hivePath), true);

		int lineCount = 0;
		while (listFiles.hasNext()) {
			LocatedFileStatus next = listFiles.next();
			Path path = next.getPath();
			String fileName = path.getName();

			if (!fileName.equals("_SUCCESS")) {
				logger.info(fileName + "---" + path.toString());

				InputStream in = fs.open(path);
				BufferedReader reader = new BufferedReader(new InputStreamReader(in));

				String str;

				while ((str = reader.readLine()) != null) {
					lineCount++;
					fileProducer.writeToLocal(str, ColumnTypeUtil.getNameTypeMap());
				}
			}
		}
		logger.info("Total line count:" + lineCount);
		fileProducer.finish();

		if (Boolean.parseBoolean(properties.getProperty("create.task"))) {
			scpUtil.putFile(dataFile.getPath(), "/tmp");
			String result = MyHttpConnection.postData("http://" + DruidUtil.getOverlordIp(properties.getProperty("overlord.ip").split(",")) + "/druid/indexer/v1/task",
							FileUtils.readFileToString(new File("resource/task/access_record.json")));
			logger.info("create task caution add, result: \n" + result);
		}
	}
}
