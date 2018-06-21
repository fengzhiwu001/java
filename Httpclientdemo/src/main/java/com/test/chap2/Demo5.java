package com.test.chap2;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
/***
 在爬取网页的时候，有的目标站点有反爬虫机制，对于频繁访问站点以及规则性访问站点的行为，会采集屏蔽IP措施。<br>
这时候，代理IP就派上用场了。<br>
关于代理IP的话 也分几种 透明代理、匿名代理、混淆代理、高匿代理<br>
1、透明代理(Transparent Proxy)<br>
REMOTE_ADDR = Proxy IP<br>
HTTP_VIA = Proxy IP<br>
HTTP_X_FORWARDED_FOR = Your IP<br>
透明代理虽然可以直接“隐藏”你的IP地址，但是还是可以从HTTP_X_FORWARDED_FOR来查到你是谁。<br>
2、匿名代理(Anonymous Proxy)<br>
REMOTE_ADDR = proxy IP<br>
HTTP_VIA = proxy IP<br>
HTTP_X_FORWARDED_FOR = proxy IP<br>
匿名代理比透明代理进步了一点：别人只能知道你用了代理，无法知道你是谁。<br>
还有一种比纯匿名代理更先进一点的：混淆代理，见下节。<br>
3、混淆代理(Distorting Proxies)<br>
REMOTE_ADDR = Proxy IP<br>
HTTP_VIA = Proxy IP<br>
HTTP_X_FORWARDED_FOR = Random IP address<br>
如上，与匿名代理相同，如果使用了混淆代理，别人还是能知道你在用代理，但是会得到一个假的IP地址，伪装的更逼真：-）<br>
4、高匿代理(Elite proxy或High Anonymity Proxy)<br>
REMOTE_ADDR = Proxy IP<br>
HTTP_VIA = not determined<br>
HTTP_X_FORWARDED_FOR = not determined<br>
可以看出来，高匿代理让别人根本无法发现你是在用代理，所以是最好的选择。<br>
一般我们搞爬虫 用的都是 高匿的代理IP；<br>
那代理IP 从哪里搞呢 很简单  百度一下，你就知道 一大堆代理IP站点。  一般都会给出一些免费的，但是花点钱搞收费接口更加方便；<br>
比如 http://www.66ip.cn/<br>
建议大家用国内代理IP 以及主干道网络大城市的代理IP 访问速度快；<br>
可以使用了  西刺免费代理IP  建议使用国内的IP  大城市的
*/
public class Demo5 {
	public static void main(String[] args) throws Exception {
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpGet httpGet = new HttpGet("https://www.taobao.com/");
		//使用代理 开始
		HttpHost proxy =new HttpHost("221.228.17.172", 8181);
		////把代理设置到请求配置
		RequestConfig build = RequestConfig.custom().setProxy(proxy).build();
		httpGet.setConfig(build);
		//使用代理 结束
		httpGet.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:50.0) Gecko/20100101 Firefox/50.0");
		CloseableHttpResponse response = httpClient.execute(httpGet);
		HttpEntity entity = response.getEntity();
		System.out.println("Content-Type  "+response.getStatusLine().getStatusCode());//得到响应的状态
		System.out.println("Content-Type  "+EntityUtils.toString(entity, "UTF-8"));//得到响应的内容
		response.close(); // response关闭
		httpClient.close(); // httpClient关闭
	}
}
