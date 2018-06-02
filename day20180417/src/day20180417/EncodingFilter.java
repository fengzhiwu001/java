package day20180417;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
/**
 * 过滤器的执行顺序 根据web.xml配置文件中的顺序来进行读取的，先读取的是构造器和init方法，这两个方法在所有的过滤器中执行完毕后，<br>
 * 才开始执行difilter的方法,在web.xml中配置顺序，先执行chain.doFilter方法前的代码，每一个过滤器都会执行。然后在逆时针执行<br>
 * chain.doFilter方法后的代码。(意思是如果在web.xml中的配置文件中排后的将会被先执行)这个排列顺序指的是url的访问路径的顺序。
 *
 */
public class EncodingFilter implements Filter {
	
	private String encoding;
	

    public String getEncoding() {
		return encoding;
	}

	public void setEncoding(String encoding) {
		this.encoding = encoding;
	}

	public EncodingFilter() {
       System.out.println("EncodingFilter......................");
    }

    public void destroy() {
    	 System.out.println("EncodingFilter......................destroy");
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		System.out.println("EncodingFilter** 执行doFilter()方法之前");
		chain.doFilter(request, response);
		System.out.println("EncodingFilter** 执行doFilter()方法之后");
		System.out.println("得到的编码类型是   ："+encoding);
	}

	public void init(FilterConfig fConfig) throws ServletException {
		encoding=fConfig.getInitParameter("charset");
		 System.out.println("EncodingFilter......................init    "+encoding);
	}

}
