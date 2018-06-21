package com.day20180606;
/**
* 等待唤醒机制<br>
* 因为这里只有两个线程，所以如果一个线程处于等待状态的话，那么另外的一个线程肯定会处于执行状态。并且这个线程wait之前，
* 必须唤醒其他线程，否则都会处于等待状态。<br>
* 这个例子必须设置一个情景，这里设置的是，如果资源对象的标志是false来进行输入，如果是true来进行输出。并且输入和输出后
* 状态会进行设置相反。以便交替执行输入和输出的目的<br>
* <br>
* 涉及的方法：<br>
<br>
1，wait(): 让线程处于冻结状态，被wait的线程会被存储到线程池中。<br>
2，notify():唤醒线程池中一个线程(任意).<br>
3，notifyAll():唤醒线程池中的所有线程。<br>
<br>
这些方法都必须定义在同步中。<br>
因为这些方法是用于操作线程状态的方法。<br>
必须要明确到底操作的是哪个锁上的线程。<br>
<br>
为什么操作线程的方法wait notify notifyAll定义在了Object类中？<br> 
因为这些方法是监视器的方法。监视器其实就是锁。<br>
锁可以是任意的对象，任意的对象调用的方式一定定义在Object类中。<br>
 */
public class InputOutStream3 {

	public static void main(String[] args) {
		Res3 res=new Res3();
		Input3 input =new Input3(res);
		Output3 output =new Output3(res);
		new Thread(input).start();
		new Thread(output).start();
	}
}

/**
 *定义资源的类
 */
class Res3{
	String name;
	String sex;
	boolean biaozhi=false;//判断标志  这里默认假时执行输入命令
}

/**
 *写入类
 */
class Input3 implements Runnable{
	Res3 res;
	boolean flag=true;
	public Input3(Res3 res) {
		this.res=res;
	}
	@Override
	public void run() {
		while(true){
			synchronized(res){
				if(res.biaozhi){//如果资源标志是真的话，等待
					
					try {
						res.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					
				}else{//标志是假的话，执行输入信息
					if(flag){
						res.name="mike";
						res.sex="man";
						flag=false;
					}else{
						res.name="丽丽";
						res.sex="女女女";
						flag=true;
					}
					
					//执行完毕的话，需要把标志设置成真，方便输出操作，输出的操作正好是输入操作的反操作
					res.biaozhi=true;
					res.notify();//叫醒的是对象，而不是本身
				}
					
				
				
			}
			
		}
	
		
	}
}

/**
 *读取资源类
 */
class Output3 implements Runnable{
	Res3 res;
	public Output3(Res3 res) {
		this.res=res;
	}
	@Override
	public void run() {
		while(true){
			synchronized(res){//作为锁的对象的设置需要进行注意
				if(!res.biaozhi){
					//输出操作和输入操作正好相反
					try {
						res.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					
				}else{
					System.out.println(Thread.currentThread().getName()+"  "+res.name+"  "+res.sex);
					
					res.biaozhi=false;
					res.notify();//设置标志，方便让输入信息进行输入，并且叫醒对象
				}
				
			}
			
		}
	
		
	}
}