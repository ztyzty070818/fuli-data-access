package io.sugo.util;

import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.SCPClient;

import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by zty on 18-11-1
 */
public class ScpUtil {

	//数据服务器的ip地址
	private String dataServerIp;
	//数据服务器的用户名
	private String dataServerUsername;
	//数据服务器的密码
	private String dataServerPassword;

	public ScpUtil(String serverIp, String userName, String password) {
		this.dataServerIp = serverIp;
		this.dataServerUsername = userName;
		this.dataServerPassword = password;
	}


	public boolean putFile(String localFile, String destDir) {
		Connection conn = new Connection(dataServerIp);
		try {
			conn.connect();
			boolean isAuthenticated = conn.authenticateWithPassword(dataServerUsername, dataServerPassword);
			if (!isAuthenticated)
				throw new IOException("Authentication failed.文件scp到数据服务器时发生异常");
			SCPClient client = new SCPClient(conn);
			client.put(localFile, destDir);
			conn.close();
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public boolean getFile(String destFile, String localDir) {
		Connection conn = new Connection(dataServerIp);
		try {
			conn.connect();
			boolean isAuthenticated = conn.authenticateWithPassword(dataServerUsername, dataServerPassword);
			if (!isAuthenticated)
				throw new IOException("Authentication failed.文件scp到数据服务器时发生异常");
			SCPClient client = new SCPClient(conn);
			client.get(destFile, localDir);
			conn.close();
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}


	public static void main(String[] args) {
		ScpUtil scpUtil = new ScpUtil("192.168.0.224", "root", "123456");
		scpUtil.getFile("/tmp/update.json", ".");
	}
}
