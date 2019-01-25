package io.sugo.order;


import com.google.common.collect.Lists;

import java.io.IOException;
import java.util.List;

/**
 * Created by zty on 18-11-19
 */
public class Tess {
	public static void main(String[] args) throws IOException, InterruptedException {
		String tableName = "visit4_visitor";

		String columnString = "`id`\n" +
						"`visit_id`\n" +
						"`name`\n" +
						"`uid`\n" +
						"`phone`\n" +
						"`idcard`\n" +
						"`qrcode_id`\n" +
						"`qrcode_uuid`\n" +
						"`status`\n";

		List<String> columnList = Lists.newArrayList(columnString.replaceAll("`", "").split("\n"));

		System.out.println("select");
		for (int i=0; i<columnList.size(); i++) {
			String column = columnList.get(i);
			if(i == columnList.size() -1 ) {
				System.out.println(column + " AS " + tableName + "__" + column);
			} else {
				System.out.println(column + " AS " + tableName + "__" + column + ",");
			}
		}
		System.out.println("from " + tableName);
	}
}
