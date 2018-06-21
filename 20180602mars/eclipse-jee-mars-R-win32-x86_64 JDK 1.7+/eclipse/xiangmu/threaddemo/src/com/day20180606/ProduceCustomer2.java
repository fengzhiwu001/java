package com.day20180606;

/**
 *这个是最原始的代码  ProduceCustomer类是按照这个类来写的
 */
public class ProduceCustomer2 {

	public static void main(String[] args) {
		Resource r = new Resource();
		Producer pro = new Producer(r);
		Consumer con = new Consumer(r);

		Thread t0 = new Thread(pro);
		Thread t1 = new Thread(pro);
		Thread t2 = new Thread(con);
		Thread t3 = new Thread(con);
		t0.start();
		t1.start();
		t2.start();
		t3.start();

	}
}


/***
 * 资源类，这个类提供生产和消费对应的方法
 * @author Administrator
 *
 */
class Resource
{
	private String name;
	private int count = 1;
	private boolean flag = false;
	public synchronized void set(String name)//  
	{
		while(flag)
			try{this.wait();}catch(InterruptedException e){}//   t1    t0
		
		this.name = name + count;//烤鸭1  烤鸭2  烤鸭3
		count++;//2 3 4
		System.out.println(Thread.currentThread().getName()+"...生产者..."+this.name);//生产烤鸭1 生产烤鸭2 生产烤鸭3
		flag = true;
		notifyAll();
	}

	public synchronized void out()//  t3
	{
		while(!flag)
			try{this.wait();}catch(InterruptedException e){}	//t2  t3
		System.out.println(Thread.currentThread().getName()+"...消费者........"+this.name);//消费烤鸭1
		flag = false;
		notifyAll();
	}
}


/**
 *生产者
 */
class Producer implements Runnable
{
	private Resource r;
	Producer(Resource r)
	{
		this.r = r;
	}
	public void run()
	{
		while(true)
		{
			r.set("烤鸭");
		}
	}
}

/**
 * 消费者
 *
 */
class Consumer implements Runnable
{
	private Resource r;
	Consumer(Resource r)
	{
		this.r = r;
	}
	public void run()
	{
		while(true)
		{
			r.out();
		}
	}
}