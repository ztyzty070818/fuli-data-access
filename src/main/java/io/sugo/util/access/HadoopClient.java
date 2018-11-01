package io.sugo.util.access;

import ch.ethz.ssh2.SCPClient;
import io.sugo.util.ScpUtil;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.LocatedFileStatus;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.fs.RemoteIterator;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;

/**
 * Created by zty on 18-10-18
 */
public class HadoopClient {
	public static ScpUtil scpUtil = new ScpUtil("192.168.0.224", "root", "123456");


	public static void readAndWriteToLocalAndWorker(String tableName, String type, String colStr) throws Exception {
		Configuration conf = new Configuration();
		FileSystem fs = FileSystem.get(new URI("hdfs://localhost"), conf, "qwe");

		ColumnType columnType = new ColumnType(type, colStr);
		columnType.writeToAccessJson();

		File file = new File("resource/test-" + type + ".csv");
		if(file.exists()) {
			file.delete();
		}
		file.createNewFile();

		RemoteIterator<LocatedFileStatus> listFiles = fs.listFiles(new Path("/user/hive/warehouse/" + tableName), true);

		while (listFiles.hasNext()) {
			LocatedFileStatus next = listFiles.next();
			String name = next.getPath().getName();
			Path path = next.getPath();
			System.out.println(name + "---" + path.toString());

			InputStream in = fs.open(path);


			BufferedReader reader = new BufferedReader(new InputStreamReader(in));

			String str;
			int maxSize = 1000000000;
			int count = 0;


			while( (str=reader.readLine()) != null) {
//				recordList.add(FileProducer.getDataMap(str));
				FileProducer.writeToLocal(file, str, columnType);
				count++;

				if(count >= maxSize) break;
			}
//			System.out.println(objectMapper.writeValueAsString(recordList));
		}
		FileProducer.finish();

		scpUtil.putFile(file.getPath(), "/data1/csv");

	}

}
