package io.sugo.access;

import com.google.common.collect.Lists;
import io.sugo.util.ScpUtil;
import io.sugo.util.http.MyHttpConnection;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

/**
 * Created by zty on 18-11-7
 */
public class Main {

	static {
		System.setProperty("log.path", "log");
	}
	private static final Logger logger = LoggerFactory.getLogger(Main.class);

	public static void main(String[] args) throws Exception {
		System.setProperty("log.path", "log");

		Properties properties = new Properties();
		properties.load(new FileInputStream("conf/system.properties"));

		String workerIp = getWorkerIp(properties);

		ScpUtil scpUtil = new ScpUtil(
						workerIp.split(":")[0],
						properties.getProperty("worker.user"),
						properties.getProperty("worker.pw"));

		changeTaskWorkerIp(workerIp);


		List<String> typeList = Lists.newArrayList(properties.getProperty("access.type").split(","));

		for(String type : typeList) {
			Client.addAndUpdate(type, scpUtil);
		}
	}

	public static String getWorkerIp(Properties properties) throws InterruptedException, IOException {
		if (properties.containsKey("worker.ip")) {
			String workerIp = properties.getProperty("worker.ip");
			if (StringUtils.isNotBlank(workerIp)) {
				return workerIp;
			}
		}
		for (int j = 0; j < 10; j++) {
			String response = MyHttpConnection.getData("http://" + Client.getOverlordIp(properties.getProperty("overlord.ip").split(",")) + "/druid/indexer/v1/workers");

			JSONArray jsonArray = new JSONArray(response);

			for (int i = 0; i < jsonArray.length(); i++) {
				JSONObject item = jsonArray.getJSONObject(i);
				if (item.getInt("currCapacityUsed") == 0) {
					return item.getJSONObject("worker").getString("host");
				}
			}
			logger.info("wait for worker...");
			Thread.sleep(60000);
		}

		return null;
	}

	public static void changeTaskWorkerIp(String workerIp) throws IOException {
		String orderAdd = FileUtils.readFileToString(new File("resource/task/order-add.json"));
		String goodAdd = FileUtils.readFileToString(new File("resource/task/good-add.json"));

		JSONObject orderAddObject = new JSONObject(orderAdd);
		JSONObject goodAddObject = new JSONObject(goodAdd);

		orderAddObject.put("worker", workerIp);
		goodAddObject.put("worker", workerIp);

		FileUtils.writeStringToFile(new File("resource/task/order-add.json"), orderAddObject.toString(2));
		FileUtils.writeStringToFile(new File("resource/task/good-add.json"), goodAddObject.toString(2));
	}


}
