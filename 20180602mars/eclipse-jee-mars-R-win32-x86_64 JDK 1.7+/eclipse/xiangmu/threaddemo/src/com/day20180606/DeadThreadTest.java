package com.day20180606;

/**
 *死锁测试  这个是根据DeadLock写的
 */
public class DeadThreadTest {
	
	public static void main(String[] args) {
		Sisuo sisuo1 =new Sisuo();
		Sisuo sisuo2 =new Sisuo();
		sisuo1.flag="1";
		sisuo2.flag="0";
		new Thread(sisuo1).start();
		new Thread(sisuo2).start();
	}
	
}

class Sisuo implements Runnable{
    public static Object obj1 =new Object();
    public static Object obj2=new Object();
    //定义两个锁  必须使用的是static来进行修饰，否则效果有问题
	
    public String flag="0";
    
	@Override
	public void run() {
		System.out.println("flag=" + flag); 
		
		if("1".equals(flag)){
			synchronized (obj1) {
					try {
						Thread.sleep(500);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				synchronized(obj2){
					try {
						Thread.sleep(500);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}
		
		if("0".equals(flag)){
			synchronized(obj2){
					try {
						Thread.sleep(500);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				synchronized (obj1) {
					try {
						Thread.sleep(500);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}
		
	}
	
}
