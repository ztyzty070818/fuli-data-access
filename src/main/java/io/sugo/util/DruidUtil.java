package io.sugo.util;

import io.sugo.order.Client;
import io.sugo.util.http.MyHttpConnection;
import org.apache.commons.lang.StringUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Properties;

/**
 * Created by zty on 19-1-19
 */
public class DruidUtil {

	private static final Logger logger = LoggerFactory.getLogger(DruidUtil.class);

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
				int capacity = item.getJSONObject("worker").getInt("capacity");
				int currCapacityUsed = item.getInt("currCapacityUsed");

				if (currCapacityUsed < capacity) {
					return item.getJSONObject("worker").getString("host");
				}
			}
			logger.info("wait for worker...");
			Thread.sleep(60000);
		}
		return null;
	}
}
