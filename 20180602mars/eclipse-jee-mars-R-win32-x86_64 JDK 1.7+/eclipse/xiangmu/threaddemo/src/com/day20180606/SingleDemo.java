package com.day20180606;

/**
 *单例设计模式
 */
public class SingleDemo {

	
}

/**
 * 饿汉式
 * @author Administrator
 *
 */
class Single
{
	private static final Single s = new Single();
	private Single(){}
	public static Single getInstance()
	{
		return s;
	}
}

/**
 *懒汉式
 *加入同步为了解决多线程安全问题。
 *加入双重判断是为了解决效率问题。
 */


class SingleLan
{
	private static SingleLan s = null;

	private SingleLan(){}

	public static SingleLan getInstance()
	{
		if(s==null)
		{
			synchronized(Single.class)		
			{
				if(s==null)
		//				-->0 -->1
					s = new SingleLan();
			}
		}
		return s;
	}
}