package com.test.newInter;

/**
 *jdk8接口的新方法 ，可以提供默认的方法
 */
public interface MyInter1 {

	/**
	 * default 需要使用default的方法
	 * @return
	 */
	default String getName(){
		return "MyInter1";
	}
}
