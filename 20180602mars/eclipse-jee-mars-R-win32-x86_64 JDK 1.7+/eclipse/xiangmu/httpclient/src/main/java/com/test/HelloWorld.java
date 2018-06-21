package com.test;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

/**
 * 第一个示例demo例子
 *
 */
public class HelloWorld {
  
	public static void main(String[] args) throws Exception {
		CloseableHttpClient httpClient = HttpClients.createDefault(); // 创建httpClient实例
		HttpGet HttpGet =new HttpGet("http://www.pgbwg.cn:8010/");//// 创建httpget实例  提供一个可访问的路径
//		HttpGet HttpGet =new HttpGet("http://www.open1111.com");//// 创建httpget实例  提供一个可访问的路径
		CloseableHttpResponse response = httpClient.execute(HttpGet);//得到一个响应
		//传入的值是HttpUriRequest接口,但是HttpGet是其实现
		HttpEntity entity = response.getEntity();//得到返回实体
		String returnValue = EntityUtils.toString(entity, "UTF-8");//得到的实体，可以结果乱码的问题
		System.out.println(returnValue);//输出访问的页面的信息
		response.close(); // response关闭
		httpClient.close(); // httpClient关闭
		
	}
}
