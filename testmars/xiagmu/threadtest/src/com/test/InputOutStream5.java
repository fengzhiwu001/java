package com.test;

/**
 * InputOutStream3的优化
 */
public class InputOutStream5 {

	public static void main(String[] args) {
		Resource5 res = new Resource5();
		Input5 input = new Input5(res);
		Output5 output = new Output5(res);
		new Thread(input).start();
		new Thread(output).start();
	}
}


class Resource5
{
	private String name;
	private String sex;
	private boolean flag = false;

	public synchronized void set(String name,String sex)
	{
		if(flag)
			try{this.wait();}catch(InterruptedException e){}
		this.name = name;
		this.sex = sex;
		flag = true;
		this.notify();
	}

	public synchronized void out()
	{
		if(!flag)
			try{this.wait();}catch(InterruptedException e){}
		System.out.println(name+"...+...."+sex);
		flag = false;
		notify();
	}
}


//输入
class Input5 implements Runnable
{
	Resource5 r ;
//	Object obj = new Object();
	Input5(Resource5 r)
	{
		this.r = r;
	}
	public void run()
	{
		int x = 0;
		while(true)
		{
			if(x==0)
			{
				r.set("mike","nan");
			}
			else
			{
				r.set("丽丽","女女女女女女");
			}
			x = (x+1)%2;
		}
	}
}
//输出
class Output5 implements Runnable
{

	Resource5 r;
//	Object obj = new Object();
	Output5(Resource5 r)
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
