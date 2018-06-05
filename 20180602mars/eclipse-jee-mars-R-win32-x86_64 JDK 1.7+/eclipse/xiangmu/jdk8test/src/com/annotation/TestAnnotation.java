package com.annotation;

import java.lang.reflect.Method;

import org.junit.Test;

public class TestAnnotation {

	@MyAnnotation("hello")
	@MyAnnotation("world")
	public void get(){
		
	}
	
	/**
	 * 得到注解的参数
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 */
	@Test
	public void test1() throws NoSuchMethodException, SecurityException{
		Class<TestAnnotation>  clazz=TestAnnotation.class;//利用反射得到类对象
		Method method = clazz.getMethod("get");//得到反射得到的方法
		MyAnnotation[] annotations = method.getAnnotationsByType(MyAnnotation.class);
		//得到方法中得到的注解的类名称 MyAnnotation这个是注解的名称
		for (MyAnnotation myAnnotation : annotations) {
			System.out.println(myAnnotation.value());
		}
		
	}
}
