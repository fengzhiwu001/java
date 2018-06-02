package day20180417;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/***
 * 简单的过滤器设置<br>
 * SimpleFilter()初始化<br>
 * 得 到web.xml配置中设置的参数：zheshiwebxmlzhongshezhide<br>
 *  init()init方法<br>
 *  在web.xml中的配置不管是过滤所有的，还是只是过滤特定的路径程序启动都会执行上边的代码。如果程序是强关的话，不会看到程序过滤器销毁的情况。
 *  如果是点击stop的话，可以看到执行destory的方法。
 */
public class SimpleFilter implements Filter {

    public SimpleFilter() {
       System.out.println("SimpleFilter()。。。。。。。。。。。。。。。。。。。。。。。。。。。。。");
    }

	public void destroy() {
		  System.out.println("SimpleFilter  destroy()销毁   destroy");
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		System.out.println("** 执行doFilter()方法之前") ;
		chain.doFilter(request, response);
		System.out.println("** 执行doFilter()方法之后") ;
	}

	public void init(FilterConfig fConfig) throws ServletException {
		String initParameter = fConfig.getInitParameter("canshu");
		System.out.println("得到web.xml配置中设置的参数："+initParameter);
		 System.out.println("SimpleFilter ......init()init方法");
	}

}
