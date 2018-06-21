package com.day20180606;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
jdk1.5以后将同步和锁封装成了对象。 <br>
并将操作锁的隐式方式定义到了该对象中， <br>
将隐式动作变成了显示动作。 <br>
 <br>
Lock接口： 出现替代了同步代码块或者同步函数。将同步的隐式锁操作变成现实锁操作。 <br>
同时更为灵活。可以一个锁上加上多组监视器。 <br>
lock():获取锁。 <br>
unlock():释放锁，通常需要定义finally代码块中。 <br>
 <br>
Condition接口：出现替代了Object中的wait notify notifyAll方法。 <br>
			将这些监视器方法单独进行了封装，变成Condition监视器对象。 <br>
			可以任意锁进行组合。 <br>
await(); <br>
signal(); <br>
signalAll(); <br>

 */
public class ProduceCustomerJDk5 {

	public static void main(String[] args) {
		ResPc1 res=new ResPc1();
		Producter1 producter =new Producter1(res);
		Customer1  customer =new Customer1(res);
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
class ResPc1{
	private String name;
	private  int count =1;
	private boolean flag =false;
	
	Lock lock=new ReentrantLock();//Lock是一个接口，需要使用它的实现类来创建对象
	//通过已有的锁获取两组监视器，一组监视生产者，一组监视消费者。
	Condition producer_con =lock.newCondition();
    Condition cus_con = lock.newCondition();

	
	
	/**
	 * 生产
	 * @param name
	 */
	public  void set(String name){
		lock.lock();
		
		try {
			while(flag)//注意while的写法
				try{producer_con.await();}catch(InterruptedException e){}//producer_con.await()调用的等待的方法
				this.name=name+count;
				System.out.println(Thread.currentThread().getName()+"...生产者..."+this.name);
				count++;
				flag=true;
				cus_con.signalAll();//唤醒方法  需要注意的是对象是不同的
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			lock.unlock();
		}
		
	}
	
	/**
	 * 消费
	 */
	public  void out()
	{
		lock.lock();
		try {
			while (!flag)
				try {
					cus_con.await();
				} catch (InterruptedException e) {
				}
			System.out.println(Thread.currentThread().getName() + "...消费者........" + this.name);
			flag = false;
			 producer_con.signalAll();
		} finally {
			lock.unlock();
		}
	}
}

/**
 *生产者
 */
class Producter1 implements Runnable{
	ResPc1 res;
	 Producter1(ResPc1 res) {
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
class Customer1 implements Runnable{
	ResPc1 res;
	 Customer1(ResPc1 res) {
		this.res=res;
	}
	@Override
	public void run() {
		while(true){
			res.out();
		}
		
	}
}