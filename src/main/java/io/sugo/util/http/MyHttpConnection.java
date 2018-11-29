package io.sugo.util.http;

import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.HttpHostConnectException;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MyHttpConnection {

	static CloseableHttpClient httpClient;

	private static Logger logger = LoggerFactory.getLogger(MyHttpConnection.class);

	public static CloseableHttpClient getClient() {
		if (httpClient == null) {
			httpClient = HttpClients.createDefault();
		}
		return httpClient;
	}

	public static int postDataStatus(String url, String params) {
		int status = -1;
		// 创建httpClient实例
		CloseableHttpClient httpClient = getClient();

		// 创建httpPost远程连接实例
		HttpPost httpPost = new HttpPost(url);
		// 配置请求参数实例
		RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(35000)// 设置连接主机服务超时时间
						.setConnectionRequestTimeout(35000)// 设置连接请求超时时间
						.setSocketTimeout(60000)// 设置读取数据连接超时时间
						.build();
		// 为httpPost实例设置配置
		httpPost.setConfig(requestConfig);
		// 设置请求头
		httpPost.addHeader("Content-Type", "application/json");
		httpPost.addHeader("Connection", "Keep-Alive");

		// 封装post请求参数
		if (null != params && params.length() > 0) {
			// 为httpPost设置封装好的请求参数
			httpPost.setEntity(new StringEntity(params, "UTF-8"));
		}
		// httpClient对象执行post请求,并返回响应参数对象
		try (CloseableHttpResponse httpResponse = httpClient.execute(httpPost)) {
			status = httpResponse.getStatusLine().getStatusCode();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return status;
	}

	public static String postData(String url, String params) {
		String result = "";
		// 创建httpClient实例
		CloseableHttpClient httpClient = getClient();

		logger.info("HTTP POST: \n" + url + "\nDATA: \n" + params);

		// 创建httpPost远程连接实例
		HttpPost httpPost = new HttpPost(url);
		// 配置请求参数实例
		RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(35000)// 设置连接主机服务超时时间
				.setConnectionRequestTimeout(35000)// 设置连接请求超时时间
				.setSocketTimeout(60000)// 设置读取数据连接超时时间
				.build();
		// 为httpPost实例设置配置
		httpPost.setConfig(requestConfig);
		// 设置请求头
		httpPost.addHeader("Content-Type", "application/json");
		httpPost.addHeader("Connection", "Keep-Alive");

		// 封装post请求参数
		if (null != params && params.length() > 0) {
			// 为httpPost设置封装好的请求参数
			httpPost.setEntity(new StringEntity(params, "UTF-8"));
		}
		// httpClient对象执行post请求,并返回响应参数对象
		try (CloseableHttpResponse httpResponse = httpClient.execute(httpPost)) {
			// 从响应对象中获取响应内容
			HttpEntity entity = httpResponse.getEntity();
			result = EntityUtils.toString(entity);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}

	public static String getData(String url) throws IOException {
		String result = "";
		// 创建httpClient实例
		CloseableHttpClient httpClient = getClient();

		logger.info("HTTP GET: \n" + url);

		// 创建httpGet远程连接实例
		HttpGet httpGet = new HttpGet(url);
		// 配置请求参数实例
		RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(35000)// 设置连接主机服务超时时间
				.setConnectionRequestTimeout(35000)// 设置连接请求超时时间
				.setSocketTimeout(60000)// 设置读取数据连接超时时间
				.build();
		// 为httpGet实例设置配置
		httpGet.setConfig(requestConfig);

		// httpClient对象执行get请求,并返回响应参数对象
		try (CloseableHttpResponse httpResponse = httpClient.execute(httpGet)) {
			// 从响应对象中获取响应内容
			HttpEntity entity = httpResponse.getEntity();
			result = EntityUtils.toString(entity);
		} catch (HttpHostConnectException ignored) {
		}
		return result;
	}

	public static String delete(String url) {
		String result = "";
		// 创建httpClient实例
		CloseableHttpClient httpClient = getClient();

		logger.info("HTTP DELETE: \n" + url);

		// 创建httpDelete远程连接实例
		HttpDelete httpDelete = new HttpDelete(url);
		// 配置请求参数实例
		RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(35000)// 设置连接主机服务超时时间
				.setConnectionRequestTimeout(35000)// 设置连接请求超时时间
				.setSocketTimeout(60000)// 设置读取数据连接超时时间
				.build();
		// 为httpDelete实例设置配置
		httpDelete.setConfig(requestConfig);

		// httpClient对象执行delete请求,并返回响应参数对象
		try (CloseableHttpResponse httpResponse = httpClient.execute(httpDelete)) {
			// 从响应对象中获取响应内容
			HttpEntity entity = httpResponse.getEntity();
			if(entity == null) {
				logger.info("response entity is null");
				return null;
			}
			result = EntityUtils.toString(entity);
			logger.info("STATUS: \n" + httpResponse.getStatusLine().getStatusCode());
			logger.info("RESULT: \n" + result);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}

	public static int deleteStatus(String url) {
		int status = -1;
		// 创建httpClient实例
		CloseableHttpClient httpClient = getClient();

		// 创建httpDelete远程连接实例
		HttpDelete httpDelete = new HttpDelete(url);
		// 配置请求参数实例
		RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(35000)// 设置连接主机服务超时时间
						.setConnectionRequestTimeout(35000)// 设置连接请求超时时间
						.setSocketTimeout(60000)// 设置读取数据连接超时时间
						.build();
		// 为httpDelete实例设置配置
		httpDelete.setConfig(requestConfig);

		// httpClient对象执行delete请求,并返回响应参数对象
		try (CloseableHttpResponse httpResponse = httpClient.execute(httpDelete)) {
			// 从响应对象中获取响应内容
			status = httpResponse.getStatusLine().getStatusCode();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return status;
	}


	public static int getResponseCode(String url) {
		HttpURLConnection connection = null;
		int resultCode = 1000;
		try {
			URL realUrl = new URL(url);
			connection = (HttpURLConnection) realUrl.openConnection();
			connection.setDoOutput(true);
			connection.setDoInput(true);
			connection.setRequestMethod("GET");
			connection.connect();

			resultCode = connection.getResponseCode();
			return resultCode;
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		} finally {
			if (null != connection)
				connection.disconnect();

		}
		return resultCode;
	}
}
