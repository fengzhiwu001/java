package com.day20180606;
/**
 *多线程间的通信，这个会出现线程不安全的问题。<br>
 *多个线程在处理同一资源，但是任务却不同。<br>
 *思路  定义一个资源的类，并且定义两个读取和写入资源的类，一个写入和一个读取，如果不进行控制的话，就会读取错误。<br>
 */
public class InputOutStream {

	public static void main(String[] args) {
		Res res=new Res();
		Input input =new Input(res);
		Output output =new Output(res);
		new Thread(input).start();
		new Thread(output).start();
	}
}

/**
 *定义资源的类
 */
class Res{
	String name;
	String sex;
}

/**
 *写入类
 */
class Input implements Runnable{
	Res res;
	boolean flag=true;
	public Input(Res res) {//把资源存放进去，初始化的时候
		this.res=res;
	}
	@Override
	public void run() {
		while(true){//while的作用是如果执行多次的话，错误会更明显，更清楚
			if(flag){//设置标志时，根据真假设置不同的值，测试读取时，读取的正确性
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

/**
 *读取资源类
 */
class Output implements Runnable{
	Res res;
	public Output(Res res) {//构造方法时，传入要资源类
		this.res=res;
	}
	@Override
	public void run() {
		while(true){
			System.out.println(Thread.currentThread().getName()+"  "+res.name+"  "+res.sex);
		}
	
		
	}
}