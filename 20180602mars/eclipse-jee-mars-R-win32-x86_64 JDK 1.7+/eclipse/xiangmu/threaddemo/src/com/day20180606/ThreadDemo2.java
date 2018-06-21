package com.day20180606;
/**
如何创建一个线程呢？<br>
创建线程方式一：继承Thread类。<br>
步骤：<br>
1，定义一个类继承Thread类。<br>
2，覆盖Thread类中的run方法。<br>
3，直接创建Thread的子类对象创建线程。<br>
4，调用start方法开启线程并调用线程的任务run方法执行。<br>
可以通过Thread的getName获取线程的名称 Thread-编号(从0开始)<br>
主线程的名字就是main。<br>
*/
public class ThreadDemo2 extends Thread {
	private String name;
	ThreadDemo2(String name)
	{
		super(name);//调用父类Thread的带参数的构造方法   结果null....x=3.....name=zhangsan
		//this.name = name;  //如果是这行的话，结果zhangsan....x=5.....name=Thread-0
	}
	
	
	public void run()
	{
		for(int x=0; x<10; x++)
		{
			//for(int y=-9999999; y<999999999; y++){}
			System.out.println(name+"....x="+x+".....name="+Thread.currentThread().getName());
		}
	}
	
	public static void main(String[] args) {
		ThreadDemo2 demo1=new ThreadDemo2("zhangsan");
		demo1.start();
	}
}
