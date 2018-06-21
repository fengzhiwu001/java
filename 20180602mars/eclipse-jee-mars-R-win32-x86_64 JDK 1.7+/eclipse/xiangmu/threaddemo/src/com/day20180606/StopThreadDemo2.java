package com.day20180606;

/**
 *怎样使线程终止  两种方法 一中设置标志。另外一种使用interrupt方法，这个方法抛出异常不用在意
 */
public class StopThreadDemo2 {

	public static void main(String[] args) {
		StopThread s1=new StopThread();
		Thread t1=new Thread(s1);
		Thread t2=new Thread(s1);
		t1.start();
		t2.start();
		
		int num = 0 ;
		while(true){
			num=num+1;
			System.out.println(num);
		   if(num == 60){
			   System.out.println(num);
			  // s1.changeFlag();  //这个是设置状态，排除使用wait的方法，如果使用的话，这种方法不好使
			   t1.interrupt();//如果使用wait方法的话，使用这种方法使其唤醒
			   t2.interrupt();
			   break;
		   }	
		   
		}
		//System.out.println("over");
	}
}

class StopThread implements Runnable{
 
	private boolean flag=true;
	
	@Override
	public void run() {
		while(flag){
			try {
				wait();
			} catch (Exception e) {
				flag=false;//设置flase的作用是，如果调用interrupt方法后，让其状态进行修改
				e.printStackTrace();
			}
			System.out.println(Thread.currentThread().getName()+"        run");
		}
	}
	
	public void changeFlag(){
		flag=false;
	}
}