package day20180417;

import javax.servlet.ServletContextAttributeEvent;
import javax.servlet.ServletContextAttributeListener;

/**
 *servletContext对象属性变化的监听器。对应的页面是application_attribute_add.jsp(属性添加)<br>
 *application_attribute_remove.jsp(属性移除)<br>
 */
public class ServletContextAttributeListenerDemo implements ServletContextAttributeListener {
    public ServletContextAttributeListenerDemo() {
        System.out.println("ServletContextAttributeListenerDemo............................");
    }

	 
    public void attributeAdded(ServletContextAttributeEvent arg0)  { 
    	System.out.println("** 增加属性 --> 属性名称：" + arg0.getName() + "，属性内容：" + arg0.getValue()) ;
    }

    public void attributeRemoved(ServletContextAttributeEvent arg0)  { 
    	System.out.println("** 删除属性 --> 属性名称：" + arg0.getName() + "，属性内容：" + arg0.getValue()) ;
    }

	 
    public void attributeReplaced(ServletContextAttributeEvent arg0)  { 
    	System.out.println("** 替换属性 --> 属性名称：" + arg0.getName() + "，属性内容：" + arg0.getValue()) ;
    }
	
}
