package com.day20180606;
/**
 *yield方法，setPriority方法，join方法的使用
 *
 */
public class JoinDemo {
	public static void main(String[] args) throws InterruptedException {
		joinTest joinTest =new joinTest();
		Thread t1=new Thread(joinTest);
		Thread t2=new Thread(joinTest);
		t1.start();
		t2.start();
		//t2.setPriority(Thread.MAX_PRIORITY);
		//优先级表示重要程度或者紧急程度.但是能不能抢到资源也是不一定. 线程的优先级用1～10 表示，1的优先级最低，10的优先级最高，默认值是5
		t1.join();
//		t1.join(1);
		/*
                        程序在main线程中调用t1线程的join方法，则main线程放弃cpu控制权，并返回t1线程继续执行直到线程t1执行完毕
                       所以结果是t1线程执行完后，才到主线程执行，相当于在main线程中同步t1线程，t1执行完了，main线程才有执行的机会 
		join方法中如果传入参数，则表示这样的意思：如果A线程中掉用B线程的join(10)，则表示A线程会等待B线程执行10毫秒，10毫秒过后，A、B线程并行执行。
		需要注意的是，jdk规定，join(0)的意思不是A线程等待B线程0秒，而是A线程等待B线程无限时间，直到B线程执行完毕，即join(0)等价于join()。
		*/
		for(int x=0; x<50; x++)
		{
			System.out.println(Thread.currentThread()+"....."+x);
		}
	}
	
}

class joinTest implements Runnable{

	@Override
	public void run() {
	for(int i=0;i<10;i++){
		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println(Thread.currentThread().getName()+"    "+i);
		//Thread.yield();//让自己或者其他线程运行，并不是单纯的让给其他线程。
	}
		
	}
	
}