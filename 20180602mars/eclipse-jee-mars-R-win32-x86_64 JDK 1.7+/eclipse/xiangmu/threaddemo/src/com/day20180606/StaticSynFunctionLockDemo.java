package com.day20180606;
/**
 静态的同步函数使用的锁是  该函数所属字节码文件对象 <br>
可以用 getClass方法获取，也可以用当前  类名.class 表示。<br>
测试的思路和同步方法和同步代码块的比较，类似<br>
 *
 */
public class StaticSynFunctionLockDemo {
	public static void main(String[] args) {
		StaticTest staticTest=new StaticTest();
		Thread t1 =new Thread(staticTest);
		Thread t2 =new Thread(staticTest);
		t1.start();
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		staticTest.flag=false;
		t2.start();
	}
	
}


class StaticTest implements Runnable{
	private static int sum =100;
	boolean flag=true;
	
	
	public  static synchronized void show() {
		if(sum>0){
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			System.out.println(Thread.currentThread().getName()+"   "+sum--);	
			}
	}

	@Override
	public void run() {
		if(flag){
			 while(true){
				 // synchronized (this) {//Thread-0   0
				// synchronized (StaticTest.class) {
				// synchronized (new Object()) {//Thread-0   0
				 synchronized (this.getClass()) {
				
					if(sum>0){
						try {
							Thread.sleep(100);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					System.out.println(Thread.currentThread().getName()+"   "+sum--);	
					}
				}
			 }
		}else{
			while(true){
				show();
			}
		}
		
		
	}
}