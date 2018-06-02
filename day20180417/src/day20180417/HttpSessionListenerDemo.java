package day20180417;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * SessionListener监听器的生命周期
 *
 */
public class HttpSessionListenerDemo implements HttpSessionListener {

    public HttpSessionListenerDemo() {
         System.out.println("HttpSessionListenerDemo  .............");
    }

    public void sessionCreated(HttpSessionEvent arg0)  { 
       System.out.println("HttpSessionListenerDemo             sessionCreated:"+arg0.getSession().getId());
    }

    public void sessionDestroyed(HttpSessionEvent arg0)  { 
    	System.out.println("HttpSessionListenerDemo             sessionDestroyed:"+arg0.getSession().getId());
    }
	
}
