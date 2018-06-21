package com.day20180606;
/**
进程：正在进行中的程序(直译).<br>
线程：就是进程中一个负责程序执行的控制单元(执行路径)<br>
一个进程中可以多执行路径，称之为多线程。<br>
一个进程中至少要有一个线程。<br>
开启多个线程是为了同时运行多部分代码。<br>
每一个线程都有自己运行的内容。这个内容可以称为线程要执行的任务。<br>
多线程好处：解决了多部分同时运行的问题。<br>
多线程的弊端：线程太多回到效率的降低。<br>
其实应用程序的执行都是cpu在做着快速的切换完成的。这个切换是随机的。<br>
JVM启动时就启动了多个线程，至少有两个线程可以分析的出来。<br>
1，执行main函数的线程，<br>
		该线程的任务代码都定义在main函数中。<br>
2，负责垃圾回收的线程。<br>
*/
public class ThreadDemo1 {
	public static void main(String[] args) {

		new Demo();
		new Demo();
		new Demo();
		System.gc();
		//个人理解 System.gc()与System.runFinalization()方法增加了finalize方法执行的机会，但不可盲目依赖它们
		System.out.println("Hello World!");
	}
}

class Demo extends Object {
	public void finalize() {
         //finalize()是Object的protected方法，子类可以覆盖该方法以实现资源清理工作，GC在回收对象之前调用该方法。
		System.out.println("demo ok");
	}
}