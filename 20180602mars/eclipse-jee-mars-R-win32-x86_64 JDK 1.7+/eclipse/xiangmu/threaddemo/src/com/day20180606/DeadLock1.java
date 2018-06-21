package com.day20180606;

/**
 * 比向东视频 只知道大概的意思，需要看视频了解下
 * @author Administrator
 *
 */
public class DeadLock1 {
  public static void main(String[] args) {
	  Dead1Lock dead1Lock=new Dead1Lock();
	  Thread t1 =new Thread(dead1Lock);
	  Thread t2 =new Thread(dead1Lock);
	  t1.start();
	  try{Thread.sleep(10);}catch (InterruptedException e){}
	  dead1Lock.flag=false;
	  t2.start();
}
}

/**
 * 
 */
class Dead1Lock implements Runnable{
	private int num =100;
	public Object obj =new Object();
	public boolean flag=true;
	@Override
	public void run() {
		
		if(flag){
			while(true){
				synchronized (obj) {
				    show();	
				}
			}
			
		}else{
			while(true){
				 show();	
			}
		  
		}
	}
	
	public synchronized void show() {
		synchronized (obj) {
			if(num>0){
				try{Thread.sleep(10);}catch (InterruptedException e){}
				System.out.println(Thread.currentThread().getName()+"   "+num);
				num--;
			}
		}
	}
}