package com.day20180606;

/**
* 线程间通信的解决方法,加锁并且锁需要是同一把锁，因为在不同的类中，所以需要的是公有的类的对象的class文件
 */
public class InputOutStream2 {

	public static void main(String[] args) {
		Res1 res=new Res1();
		Input1 input =new Input1(res);
		Output1 output =new Output1(res);
		new Thread(input).start();
		new Thread(output).start();
	}
}

/**
 *定义资源的类
 */
class Res1{
	String name;
	String sex;
}

/**
 *写入类
 */
class Input1 implements Runnable{
	Res1 res;
	boolean flag=true;
	public Input1(Res1 res) {
		this.res=res;
	}
	@Override
	public void run() {
		while(true){
			synchronized(Res.class){
				//解决的方法  需要加锁，并且这个锁对象必须一致，因为在不同的类中，所以需要一个公共的资源resd对应的类
				if(flag){
					res.name="mike";
					res.sex="man";
					flag=false;
				}else{
					res.name="丽丽";
					res.sex="女女女";
					flag=true;
				}
			}
			
		}
	
		
	}
}

/**
 *读取资源类
 */
class Output1 implements Runnable{
	Res1 res;
	public Output1(Res1 res) {
		this.res=res;
	}
	@Override
	public void run() {
		while(true){
			synchronized(Res.class){
				System.out.println(Thread.currentThread().getName()+"  "+res.name+"  "+res.sex);
			}
			
		}
	
		
	}
}