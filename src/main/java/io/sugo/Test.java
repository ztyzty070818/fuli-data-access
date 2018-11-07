package io.sugo;

import com.google.common.collect.Lists;
import org.apache.commons.lang.StringUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zty on 18-10-24
 */
public class Test {
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		String url = "jdbc:sqlserver://192.168.0.43:52617;DatabaseName=azkaban";
		String user = "sa";
		String password = "123456";
		String sql = "select * from test";
//		String sql = "insert into test (id, name) values (1, \'j\')";

		Connection connection = DriverManager.getConnection(url, user, password);
		Statement smt = connection.createStatement();
		boolean hasResultSet = smt.execute(sql);

		ResultSet resultSet = smt.getResultSet();
		while (resultSet != null && resultSet.next()) {
			System.out.println(resultSet.getString(1));
			System.out.println(resultSet.getString(2));
		}
	}
}
