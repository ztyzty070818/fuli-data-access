package io.sugo.access;

import com.google.common.base.Joiner;
import com.google.common.collect.Lists;
import io.sugo.order.ColumnType;
import io.sugo.util.DruidUtil;
import io.sugo.util.http.MyHttpConnection;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * Created by zty on 18-10-22
 */
public class FileProducer {

	private BufferedWriter bufferedWriter = null;
	private Properties properties;
	private int count = 0;

	private static final Logger logger = LoggerFactory.getLogger(FileProducer.class);

	public FileProducer(File file, Properties properties) throws IOException {
		this.bufferedWriter = new BufferedWriter(new FileWriter(file, true));
		this.properties = properties;
	}



	public void writeToLocal(String item, Map<String, String> nameTypeMap) throws IOException {

		String[] strs = item.replaceAll("\\\\N", "null").split("\001", -1);

		List<String> nameList = Lists.newArrayList(nameTypeMap.keySet());

		SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM-dd");

		for (int i = 0; i < strs.length; i++) {
			String type = nameTypeMap.get(nameList.get(i));
			if (type.equals("date")) {
				String dateStr = strs[i];
				if (dateStr.length() > 0 && !dateStr.equals("null")) {
					try {
						strs[i] = String.valueOf(format1.parse(strs[i]).getTime());
					} catch (ParseException e) {
						try {
							strs[i] = String.valueOf(format2.parse(strs[i]).getTime());
						} catch (ParseException e1) {
							count++;
							break;
						}
					}
				} else {
					strs[i] = "0";
				}
			} else if (type.equals("int") || type.equals("double") || type.equals("float")) {
				String dataStr = strs[i];
				if (StringUtils.isBlank(dataStr) || dataStr.equals("null")) {
					strs[i] = "0";
				}
			}
		}
		bufferedWriter.write(Joiner.on("\u0001").join(strs) + "\n");
	}

	public void finish() throws IOException {
		if(bufferedWriter != null) {
			bufferedWriter.flush();
			bufferedWriter.close();
		}
		logger.info("unparseable: " + count);
	}
}
