package com.test.chap2;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
/***
 * 通过setHeader方法 设置key value；来得到模拟浏览器请求
 */
public class Demo2 {
	public static void main(String[] args) throws Exception {
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpGet httpGet = new HttpGet("http://www.baidu.com/");
//		HttpGet httpGet = new HttpGet("http://www.tuicool.com/");
		httpGet.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:50.0) Gecko/20100101 Firefox/50.0");
		// 设置请求头消息User-Agent
		CloseableHttpResponse response = httpClient.execute(httpGet);
		HttpEntity entity = response.getEntity();
		String returnValue = EntityUtils.toString(entity, "UTF-8");
		System.out.println(returnValue);
	}
}
