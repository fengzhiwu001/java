package com.test.chap2;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
/***
 * HttpClient获取响应内容类型Content-Type<br>
 * 当然Content-Type还有一堆，那这东西对于我们爬虫有啥用的，我们再爬取网页的时候 ，可以通过<br>
 *  Content-Type来提取我们需要爬取的网页或者是爬取的时候，需要过滤掉的一些网页；<br>
 */
public class Demo3 {
	public static void main(String[] args) throws Exception {
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpGet httpGet = new HttpGet("http://www.open1111.com/static/js/jQuery.js");
		//http://central.maven.org/maven2/HTTPClient/HTTPClient/0.3-3/HTTPClient-0.3-3.jar   类型 > Content-Type  Content-Type: application/java-archive
        //https://www.tuicool.com/        类型 > Content-Type  Content-Type: text/html; charset=utf-8
        //http://www.open1111.com/static/js/jQuery.js   类型>Content-Type  Content-Type: text/html;charset=utf-8

		httpGet.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:50.0) Gecko/20100101 Firefox/50.0");
		// 设置请求头消息User-Agent
		CloseableHttpResponse response = httpClient.execute(httpGet);
		HttpEntity entity = response.getEntity();
		System.out.println("Content-Type  "+entity.getContentType().toString());//得到获取网页的内容
		response.close(); // response关闭
		httpClient.close(); // httpClient关闭
	}
}
