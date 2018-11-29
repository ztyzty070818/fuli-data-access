package io.sugo.access;

import io.sugo.util.http.MyHttpConnection;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

/**
 * Created by zty on 18-11-19
 */
public class Tess {
	private static Logger logger = LoggerFactory.getLogger(Tess.class);
	public static void main(String[] args) throws IOException, InterruptedException {
//
//		Properties properties = new Properties();
//		properties.load(new FileInputStream("conf/system.properties"));
//		logger.info("????");
		boolean a = "3" == "3";
		System.out.println(a);
	}
}
