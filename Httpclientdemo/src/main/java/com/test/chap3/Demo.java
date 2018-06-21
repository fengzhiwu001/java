package com.test.chap3;

import java.io.File;
import java.io.InputStream;

import org.apache.commons.io.FileUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

/**
 *爬到的内容进行拷贝
 */
public class Demo {

	public static void main(String[] args) throws  Exception {
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpGet httpGet =new HttpGet("http://www.java1234.com/gg/dljd4.gif");//读取到的内容
		CloseableHttpResponse response = httpClient.execute(httpGet);
		HttpEntity entity = response.getEntity();
		if(entity!=null){
			System.out.println(entity.getContentType().getValue());//得到类型    image/gif
			InputStream inputStream = entity.getContent();//得到一个流，通过CloseableHttpResponse对象
			FileUtils.copyToFile(inputStream, new File("D://aa.gif"));//保存到本地磁盘
		}
		response.close(); // response关闭
		httpClient.close(); // httpClient关闭
	}
}
