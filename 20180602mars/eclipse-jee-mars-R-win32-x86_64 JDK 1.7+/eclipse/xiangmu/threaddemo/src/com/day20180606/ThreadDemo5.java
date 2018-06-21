package com.day20180606;

/**
 需求:储户，两个，每个都到银行存钱每次存100，，共存三次。<br>
 同步方法
*/
public class ThreadDemo5 {
	
	public static void main(String[] args) {
		Cus cus=new Cus();
		Thread t1 =new Thread(cus);
		Thread t2 =new Thread(cus);
		t1.start();
		t2.start();
	}
	
}

/**
 *银行类
 */
class Bank{
	
	private int sum=0;
	/**
	 * 具有存钱的功能
	 */
	public synchronized void add(int cf){//如果把synchronized方法去掉的话，那么显示的信息就不会是按照顺序展示
	   sum+=cf;
	   
	  try {
		Thread.sleep(10);
	} catch (InterruptedException e) {
		e.printStackTrace();
	}
		System.out.println("sum="+sum);
	}
}

/**
 *客户存三次钱
 */
class Cus implements Runnable{
	private Bank bank =new Bank();
	@Override
	public void run() {
		for(int i=0;i<3;i++){
			
			bank.add(200);
		}
	}
	
}