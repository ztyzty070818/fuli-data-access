package io.sugo.access;

import com.google.common.base.Joiner;
import com.google.common.collect.Lists;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

/**
 * Created by zty on 18-10-22
 */
public class FileProducer {

	static BufferedWriter bufferedWriter = null;
	static int count = 0;

	public static void writeToLocal(File file, String item, ColumnType columnType) throws IOException {
		if(bufferedWriter == null) {
			bufferedWriter = new BufferedWriter(new FileWriter(file, true));
		}

//		System.out.println(item);

		String[] strs = item.replaceAll("\\\\N", "").replaceAll("null", "").split("\001", -1);

		Map<String, String> nameTypeMap = columnType.getNameTypeMap();
		List<String> nameList = Lists.newArrayList(columnType.getNameTypeMap().keySet());

		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");


		for (int i = 0; i < strs.length; i++) {
			if (nameTypeMap.get(nameList.get(i)).equals("date")) {
				String dateStr = strs[i];
				if (dateStr.length() > 0) {
					try {
						strs[i] = String.valueOf(format.parse(strs[i]).getTime());
					} catch (ParseException e) {
						count++;
						break;
//						System.out.println(strs[i]);
//						System.out.println(Joiner.on("|").join(strs));
					}
				} else {
					strs[i] = "0";
				}
			}
		}
		bufferedWriter.write(Joiner.on("|").join(strs) + "\n");
//				System.out.println(nameList.get(i) + "\t" + nameTypeMap.get(nameList.get(i)) + "\t" + strs[i]);
	}

	public static void finish() throws IOException {
		if(bufferedWriter != null) {
			bufferedWriter.flush();
			bufferedWriter.close();
		}
		System.out.println("unparseable: " + count);
	}
}
