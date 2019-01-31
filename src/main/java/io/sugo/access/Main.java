package io.sugo.access;

import io.sugo.util.DruidUtil;
import io.sugo.util.ScpUtil;
import org.apache.commons.io.FileUtils;
import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

/**
 * Created by zty on 19-1-19
 */
public class Main {


	public static void main(String[] args) throws Exception {
		System.setProperty("log.path", "log");

		Properties properties = new Properties();
		properties.load(new FileInputStream("conf/system.properties"));

		String workerIp = DruidUtil.getWorkerIp(properties);
		changeTaskWorkerIp(workerIp, "access_record.json");

		ScpUtil scpUtil = new ScpUtil(
						workerIp.split(":")[0],
						properties.getProperty("worker.user"),
						properties.getProperty("worker.pw"));


		Access access = new Access(scpUtil);
		access.accessNewData();
	}


	public static void changeTaskWorkerIp(String workerIp, String taskFileName) throws IOException {
		File file = new File("resource/task/" + taskFileName);
		String addStr = FileUtils.readFileToString(file);
		JSONObject addObject = new JSONObject(addStr);
		addObject.put("worker", workerIp);
		FileUtils.writeStringToFile(file, addObject.toString(2));
	}
}
