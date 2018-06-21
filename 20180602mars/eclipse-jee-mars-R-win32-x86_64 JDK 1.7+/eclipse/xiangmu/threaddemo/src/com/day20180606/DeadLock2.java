package com.day20180606;

/**
 * 死锁的测试，这个例子和DemoLock的例子差不多  
 */
public class DeadLock2 {
	public static void main(String[] args) {
		Test t1 =new Test(true);
		Test t2 =new Test(false);
		Thread thread1=new Thread(t1);
		/*try {
			thread1.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}*/
		Thread thread2=new Thread(t2);
		thread1.start();
		thread2.start();
		
	}

	
}

/**
 *这个类的作用是提供两把锁
 */
class MyLock {
	public static final Object lock1 = new Object();
	public static final Object lock2 = new Object();
}

/**
 * 死锁的具体的实现
 *
 */
class Test implements Runnable {

	public boolean flag;

	public Test(boolean b) {//根据传参来设置boolean的值
		flag = b;
	}

	@Override
	public void run() {
		
		if(flag){
			synchronized(MyLock.lock1){
				System.out.println("MyLock.lock1");
				synchronized (MyLock.lock2) {
					System.out.println("MyLock.lock2");
				}
			}
		}else{
			synchronized(MyLock.lock2){
				System.out.println("MyLock.lock2");
				 synchronized (MyLock.lock1) {
					System.out.println("MyLock.lock1");
				}
			}
		}
		
	};
	
	
}