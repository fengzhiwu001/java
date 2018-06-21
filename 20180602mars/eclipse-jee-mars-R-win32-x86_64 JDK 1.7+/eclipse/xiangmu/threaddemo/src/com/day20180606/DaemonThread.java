package com.day20180606;
/**
java中线程分为两种类型：用户线程和守护线程。<br>
通过Thread.setDaemon(false)设置为用户线程；<br>
 通过Thread.setDaemon(true)设置为守护线程。如果不设置次属性，默认为用户线程。<br>
<br>
用户线程和守护线程的区别：<br>
1. 主线程结束后用户线程还会继续运行,JVM存活；主线程结束后守护线程和JVM的状态又下面第2条确定。
2.如果没有用户线程，都是守护线程，那么JVM结束（随之而来的是所有的一切烟消云散，包括所有的守护线程）。<br>
<br>
补充说明：<br>
<br>
定义：守护线程--也称“服务线程”，在没有用户线程可服务时会自动离开。<br>
优先级：守护线程的优先级比较低，用于为系统中的其它对象和线程提供服务。<br>
<br>
设置：通过setDaemon(true)来设置线程为“守护线程”；将一个用户线程设置为守护线程的方式是在线程启动用线程对象的setDaemon方法。<br>
<br>
example: 垃圾回收线程就是一个经典的守护线程，当我们的程序中不再有任何运行的Thread,程序就不会再产生垃圾，垃圾回收器也就无事可做，所以当垃圾回收线程是JVM上仅剩的线程时，
垃圾回收线程会自动离开。它始终在低级别的状态中运行，用于实时监控和管理系统中的可回收资源。
<br>
生命周期：守护进程（Daemon）是运行在后台的一种特殊进程。它独立于控制终端并且周期性地执行某种任务或等待处理某些发生的事件。
也就是说守护线程不依赖于终端，但是依赖于系统，与系统“同生共死”。那Java的守护线程是什么样子的呢。
当JVM中所有的线程都是守护线程的时候，JVM就可以退出了；如果还有一个或以上的非守护线程则JVM不会退出。
*/

public class DaemonThread {
  public static void main(String[] args) {
	  DaemonTest daemonTest =new DaemonTest();
	  daemonTest.setDaemon(false);//设置在调用start方法之前
	  daemonTest.start();
	  for(int i=0;i<10;i++){
		   System.out.println(Thread.currentThread().getName()+" "+i);
	  }
}
}


class DaemonTest extends Thread{
	
	@Override
	public void run() {
       for(int i=0;;i++){
    	   System.out.println(Thread.currentThread().getName()+" "+i);
       }
	}
}
