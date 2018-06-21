package com.day20180606;

/**
 * InputOutStream3的优化
 */
public class InputOutStream4 {

	public static void main(String[] args) {
		Res4 res = new Res4();
		Input4 input = new Input4(res);
		Output4 output = new Output4(res);
		new Thread(input).start();
		new Thread(output).start();
	}
}

/**
 * 定义资源的类
 */
class Res4 {
	private String name;
	private String sex;
	private boolean flag = false;// 判断标志 这里默认假时执行输入命令

	public synchronized void set(String name, String sex) {
		if (flag)// 为真，等待，输出执行  
			try{this.wait();}catch(InterruptedException e){}
			//设置相反状态，唤醒其他线程,这些是同一个状态下的操作，但是会等待下
			this.name = name;
			this.sex = sex;
			flag = true;
			this.notify();
	}

	public synchronized void out() {
		if (!flag)// 为假，等待
			try {
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
     	//  并且设置状态，唤醒其他的
			System.out.println(Thread.currentThread().getName() + "  " + this.name + "  " + this.sex);
			flag = false;
			this.notify();
	}

}

/**
 * 写入类
 */
class Input4 implements Runnable {
	Res4 res;
	boolean flag = true;

	public Input4(Res4 res) {
		this.res = res;
	}

	@Override
	public void run() {
		while (true) {
			if (flag) {
				res.set("mike", "man");
				flag = false;
			} else {
				res.set("丽丽", "女女女");
				flag = true;
			}
		}

	}
}

/**
 * 读取资源类
 */
class Output4 implements Runnable {
	Res4 res;

	public Output4(Res4 res) {
		this.res = res;
	}

	@Override
	public void run() {
		while (true) {
			res.out();
		}

	}
}