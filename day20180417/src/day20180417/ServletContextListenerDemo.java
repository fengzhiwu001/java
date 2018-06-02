package day20180417;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
/**
 * 定义一个servletContext的监听器<br>
	测试下程序中监听器和过滤器哪一个先执行  <br>
	执行的顺序是程序启动时，监听器的初始化先被执行，然后才是过滤器再次执行。<br>
	关闭项目时，过滤器的结束方法是先被执行的，然后才是监听器的结束方法才被执行。<br>
 */
public class ServletContextListenerDemo implements ServletContextListener {

    public ServletContextListenerDemo() {
 
       System.out.println("ServletContextListenerDemo..................");
    }

    public void contextDestroyed(ServletContextEvent arg0)  { 
         System.out.println("ServletContextListenerDemo   contextDestroyed被执行了");
         String contextPath = arg0.getServletContext().getContextPath();
         System.out.println("contextPath"+contextPath);
    }

    public void contextInitialized(ServletContextEvent arg0)  { 
    	 System.out.println("ServletContextListenerDemo   contextInitialized被执行了");
    	 String contextPath = arg0.getServletContext().getContextPath();
         System.out.println("contextPath"+contextPath);
    }
	
}
