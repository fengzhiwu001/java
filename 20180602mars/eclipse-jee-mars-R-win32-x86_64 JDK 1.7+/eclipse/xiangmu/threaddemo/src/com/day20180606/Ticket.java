package com.day20180606;
/**
线程安全问题产生的原因：<br>
<br>
1，多个线程在操作共享的数据。<br>
2，操作共享数据的线程代码有多条。<br>
<br>
当一个线程在执行操作共享数据的多条代码过程中，其他线程参与了运算。<br>
就会导致线程安全问题的产生。 <br>
<br>
解决思路；<br>
就是将多条操作共享数据的线程代码封装起来，当有线程在执行这些代码的时候，<br>
其他线程时不可以参与运算的。<br>
必须要当前线程把这些代码都执行完毕后，其他线程才可以参与运算。<br> 

在java中，用同步代码块就可以解决这个问题。<br>

同步代码块的格式：<br>
synchronized(对象)<br>
{<br>
	需要被同步的代码 ；<br>
}<br>
<br>
同步的好处：解决了线程的安全问题。<br>
<br>
同步的弊端：相对降低了效率，因为同步外的线程的都会判断同步锁。<br>
<br>
同步的前提：同步中必须有多个线程并使用同一个锁。<br>
*/
public class Ticket implements Runnable {
    private int total=100;
	
	@Override
	public void run() {
		synchronized (this) {//如果不使用这行代码的话，就会出现是0的结果
			while (total > 0) {
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
				System.out.println("还剩下" + total + "票,正在卖出的线程是" + Thread.currentThread().getName());
				total--;
			}

		}

	}
	
	public static void main(String[] args) {
		Ticket ticket =new Ticket();
		Thread t1 =new Thread(ticket);
		Thread t2 =new Thread(ticket);
		t1.start();
	//	t1.start(); //java.lang.IllegalThreadStateException 同一个线程不能同时启动两次，否则会报错
		t2.start();
	}

}
