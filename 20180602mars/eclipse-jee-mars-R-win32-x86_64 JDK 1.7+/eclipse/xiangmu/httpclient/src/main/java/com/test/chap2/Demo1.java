package com.test.chap2;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

/**
 * 有的会被拦截
 *
 */
public class Demo1 {

	public static void main(String[] args) throws Exception {
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpGet httpGet =new HttpGet("http://www.tuicool.com/");
		CloseableHttpResponse response = httpClient.execute(httpGet);
		HttpEntity entity = response.getEntity();
		String returnValue = EntityUtils.toString(entity, "UTF-8");
		System.out.println(returnValue);
		//系统检测亲不是真人行为，因系统资源限制，我们只能拒绝你的请求。如果你有疑问，可以通过微博 http://weibo.com/tuicool2012/ 联系我们
	}
}
