package com.day20180606;

/**
 *测试的代码 有可能立即报错，有可能执行下才会报错
 */
public class ThreadDemo3 {
	public static void main(String[] args) 
	{
		Thread3Demo d1 = new Thread3Demo("旺财");
		Thread3Demo d2 = new Thread3Demo("xiaoqiang");
		d1.start();
		
		d2.start();

		System.out.println(4/0);//throw new ArithmeticException();

		for(int x=0; x<20; x++)
		{
			System.out.println(x+"...."+Thread.currentThread().getName());
		}
	}
}

class Thread3Demo extends Thread
{
	private String name;
	Thread3Demo(String name)
	{
//		super(name);
		this.name = name;
	}
	public void run()
	{
		//int[] arr = new int[3];
		//System.out.println(arr[3]);
		for(int x=0; x<10; x++)
		{
			System.out.println("....x="+x+".....name="+Thread.currentThread().getName());
		}
	}
}