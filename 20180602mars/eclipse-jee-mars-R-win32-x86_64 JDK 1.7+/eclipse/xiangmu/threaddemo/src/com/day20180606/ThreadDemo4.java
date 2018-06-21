package com.day20180606;
/**
创建线程的第一种方式:继承Thread类。<br>
创建线程的第二种方式：实现Runnable接口。<br>
1,定义类实现Runnable接口。<br>
2，覆盖接口中的run方法，将线程的任务代码封装到run方法中。<br>
3，通过Thread类创建线程对象，并将Runnable接口的子类对象作为Thread类的构造函数的参数进行传递。<br>
	为什么？因为线程的任务都封装在Runnable接口子类对象的run方法中。<br>
	所以要在线程对象创建时就必须明确要运行的任务。<br>
4，调用线程对象的start方法开启线程。<br>
实现Runnable接口的好处：<br>
1，将线程的任务从线程的子类中分离出来，进行了单独的封装。<br>
	按照面向对象的思想将任务的封装成对象。<br>
2，避免了java单继承的局限性。<br>
所以，创建线程的第二种方式较为常用。<br>
*/
public class ThreadDemo4 {
	public static void main(String[] args) 
	{	
		Thread4Demo d = new Thread4Demo();
		Thread t1 = new Thread(d);
		Thread t2 = new Thread(d);
		t1.start();
		t2.start();
	}
}

class Thread4Demo implements Runnable  //准备扩展Demo类的功能，让其中的内容可以作为线程的任务执行。
  
{
	public void run() {
		show();
	}

	public void show() {
		for (int x = 0; x < 20; x++) {
			System.out.println(Thread.currentThread().getName() + "....." + x);
		}
	}
}