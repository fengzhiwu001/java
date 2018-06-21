package com.test.chap4;

import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

/**
 * 设置爬取时间
 */
public class Demo1 {
  public static void main(String[] args) throws Exception {
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpGet httpGet =new HttpGet("http://central.maven.org/maven2/");
		RequestConfig config=RequestConfig.custom().setConnectTimeout(10000)//设置链接超时时间 10s   单位是毫秒
		.setSocketTimeout(20000)//设置读取超时时间
		.build();
		httpGet.setConfig(config);//把设置的超时时间设置进请求中
		
		httpGet.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:50.0) Gecko/20100101 Firefox/50.0");
		CloseableHttpResponse response=httpClient.execute(httpGet); 
		HttpEntity entity=response.getEntity();
		System.out.println("网页内容："+EntityUtils.toString(entity, "utf-8"));
		response.close(); // response关闭
		httpClient.close(); // httpClient关闭
}
}
