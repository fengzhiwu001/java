package com.day20180606;
/**
多生产者，多消费者的问题。<br>
if判断标记，只有一次，会导致不该运行的线程运行了。出现了数据错误的情况。<br>
while判断标记，解决了线程获取执行权后，是否要运行！<br>
notify:只能唤醒一个线程，如果本方唤醒了本方，没有意义。而且while判断标记+notify会导致死锁。<br>
notifyAll解决了本方线程一定会唤醒对方线程的问题。<br>

 */
public class ProduceCustomer {

	public static void main(String[] args) {
		ResPc res=new ResPc();
		Producter producter =new Producter(res);
		Customer  customer =new Customer(res);
		Thread tp1=new Thread(producter);
		Thread tp2=new Thread(producter);
		Thread tc1=new Thread(customer);
		Thread tc2=new Thread(customer);
		tp1.start();
		tp2.start();
		tc1.start();
		tc2.start();
		
	}
}


/***
 * 资源类，这个类提供生产和消费对应的方法
 * @author Administrator
 *
 */
class ResPc{
	private String name;
	private  int count =1;
	private boolean flag =false;
	
	/**
	 * 生产
	 * @param name
	 */
	public synchronized void set(String name){
		while(flag)//注意while的写法
			try{this.wait();}catch(InterruptedException e){}
			this.name=name+count;
			System.out.println(Thread.currentThread().getName()+"...生产者..."+this.name);
			count++;
			flag=true;
			notifyAll();
		
	}
	
	/**
	 * 消费
	 */
	public synchronized void out()
	{
		while(!flag)
			try{this.wait();}catch(InterruptedException e){}	
		System.out.println(Thread.currentThread().getName()+"...消费者........"+this.name);
		flag = false;
		notifyAll();
	}
}

/**
 *生产者
 */
class Producter implements Runnable{
	ResPc res;
	 Producter(ResPc res) {
		this.res=res;
	}
	@Override
	public void run() {
	
		while(true){
			res.set("烤鸭");
		}
	}
	
}

/**
 * 消费者
 *
 */
class Customer implements Runnable{
	ResPc res;
	 Customer(ResPc res) {
		this.res=res;
	}
	@Override
	public void run() {
		while(true){
			res.out();
		}
		
	}
}