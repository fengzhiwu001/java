package com.test;

/**
* �̼߳�ͨ�ŵĽ������,������������Ҫ��ͬһ��������Ϊ�ڲ�ͬ�����У�������Ҫ���ǹ��е���Ķ����class�ļ�
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
 *������Դ����
 */
class Res1{
	String name;
	String sex;
}

/**
 *д����
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
				//����ķ���  ��Ҫ����������������������һ�£���Ϊ�ڲ�ͬ�����У�������Ҫһ����������Դresd��Ӧ����
				if(flag){
					res.name="mike";
					res.sex="man";
					flag=false;
				}else{
					res.name="����";
					res.sex="ŮŮŮ";
					flag=true;
				}
			}
			
		}
	
		
	}
}

/**
 *��ȡ��Դ��
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