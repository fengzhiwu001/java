package com.day20180606;
/**
 *同步方法和同步代码块的比较，
 *这个例子的思路是，通过boolean的不同来执行同步方法和同步代码块(可以传入不同的对象，根据不同的对象来进行确认)，如果执行的结果是到1为止，
 *说明这两个锁是一样的，否则说明这两锁是不一样的<br>
 *同步方法的使用的锁是this；<br>
 *<br>
      同步方法和同步代码块的区别：<br>
      同步方法的锁是固定的this。<br>
      同步代码块的锁是任意的对象。<br>
      <br>
     建议使用同步代码块。
 */
public class SynFunctionLockDemo {
	public static void main(String[] args) {
		Ticket1 t=new Ticket1();
		
		Thread thread1=new Thread(t);
		Thread thread2=new Thread(t);
		
		thread1.start();
		try{Thread.sleep(10);}catch(InterruptedException e){}
		t.flag=false;
		thread2.start();
	}

}

/**
 * 
 *定义一个变量
 *定义一个boolean的判断标志，并且定义初始值
 *判断
 * 如果 真 while循环执行，并且加锁 判断变量是否大于0，如果大于0，休息10s,把值减1
 * 如果 假  while循环  调用show方法
 * show方法  加synchronized关键字   判断变量是否大于0，如果大于0，休息10s,把值减1
 */
class Ticket1 implements Runnable{
    public int sum=100;
    Object object=new Object();
    public boolean flag=true;
	
	@Override
	public void run() {
		
		if(flag){//如果是真，执行同步代码块
			while(true){//如果使用while的话，那么程序不会停下来
				//synchronized (object) {
						synchronized (this) {
					if(sum>0){
						
						try {
							Thread.sleep(10);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						
						System.out.println(Thread.currentThread().getName()+"  "+sum--);
					}
				}
			}
		}else{//假 执行同步方法  这个show方法就是同步方法
			while(true){
				show();
			}
			
		}
		
	}

	public synchronized void show() {
			if(sum>0){
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
				System.out.println(Thread.currentThread().getName()+"  "+sum--);
			}
		}
}
