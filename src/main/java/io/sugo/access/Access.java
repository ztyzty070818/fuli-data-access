package io.sugo.access;

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

	static {
		try {
			properties.load(new FileInputStream("conf/system.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void accessNewData() throws IOException, InterruptedException {
		String dateStr = new DateTime().minusDays(1).toString("yyyy-MM-dd");
		String hivePath = String.format("/user/hive/warehouse/access_record/day=%s", dateStr);

		Configuration conf = new Configuration();
		FileSystem fs = FileSystem
						.get(URI.create(properties.getProperty("hadoop.uri")), conf, properties.getProperty("hadoop.user"));

		if (!fs.exists(new Path(hivePath))) {
			logger.error("File does not exist: " + hivePath);
			return;
		}
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
//					fileProducer.writeToLocal(str, columnType);
				}
			}
		}
		logger.info("Total line count:" + lineCount);
//		fileProducer.finish();

//		scpUtil.putFile(dataFile.getPath(), "/tmp");
//		FileUtils.copyFile(dataFile, new File("/tmp" + dataFile.getName()));
	}
}
