package com.test.chap2;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
/***
我们HttpClient向服务器请求时，<br>
正常情况 执行成功 返回200状态码，<br>
不一定每次都会请求成功，<br>
比如这个请求地址不存在 返回404<br>
服务器内部报错 返回500<br>
有些服务器有防采集，假如你频繁的采集数据，则返回403 拒绝你请求。<br>
当然 我们是有办法的 后面会讲到用代理IP。<br>
 */
public class Demo4 {
	public static void main(String[] args) throws Exception {
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpGet httpGet = new HttpGet("http://www.open1111.com");
//		HttpGet httpGet = new HttpGet("http://www.open1111.com/aaa.jsp");
		httpGet.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:50.0) Gecko/20100101 Firefox/50.0");
		CloseableHttpResponse response = httpClient.execute(httpGet);
		HttpEntity entity = response.getEntity();
		System.out.println("Content-Type  "+response.getStatusLine().getStatusCode());//得到响应的状态
		response.close(); // response关闭
		httpClient.close(); // httpClient关闭
	}
}
