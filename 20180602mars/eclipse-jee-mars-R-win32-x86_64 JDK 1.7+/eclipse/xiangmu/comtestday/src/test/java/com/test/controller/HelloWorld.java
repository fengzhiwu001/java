package com.test.controller;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *@EnableAutoConfiguration
 * 注解:作用在于让 Spring Boot   根据应用所声明的依赖来对 Spring 框架进行自动配置
   这个注解告诉Spring Boot根据添加的jar依赖猜测你想如何配置Spring。
      由于spring-boot-starter-web添加了Tomcat和Spring MVC，
       所以auto-configuration将假定你正在开发一个web应用并相应地对Spring进行设置。<br>
   @RestController 
       在上加上RestController 表示修饰该Controller所有的方法返回JSON格式,直接可以编写
    Restful接口
 */
@EnableAutoConfiguration
@RestController
public class HelloWorld {

	@RequestMapping("/hello")
	//访问路径  http://localhost:8080/hello
	public String index() {
		return "helloworld";
	}

	/**
	 * 普通方法运行就可以启动
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(HelloWorld.class, args);
		//两个参数，一个是这个类的class文件，另外一个是这个方法的参数。
	}
}
