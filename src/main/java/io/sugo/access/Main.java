package io.sugo.access;

import io.sugo.util.DruidUtil;
import io.sugo.util.ScpUtil;

import java.io.FileInputStream;
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

		ScpUtil scpUtil = new ScpUtil(
						workerIp.split(":")[0],
						properties.getProperty("worker.user"),
						properties.getProperty("worker.pw"));
	}
}
