package com.test;

/**
 *���̼߳��ͨ�ţ����������̲߳���ȫ�����⡣
 *˼·  ����һ����Դ���࣬���Ҷ���������ȡ��д����Դ���࣬һ��д���һ����ȡ����������п��ƵĻ����ͻ��ȡ����
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
 *������Դ����
 */
class Res{
	String name;
	String sex;
}

/**
 *д����
 */
class Input implements Runnable{
	Res res;
	boolean flag=true;
	public Input(Res res) {//����Դ��Ž�ȥ����ʼ����ʱ��
		this.res=res;
	}
	@Override
	public void run() {
		while(true){//while�����������ִ�ж�εĻ������������ԣ������
			if(flag){//���ñ�־ʱ������������ò�ͬ��ֵ�����Զ�ȡʱ����ȡ����ȷ��
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

/**
 *��ȡ��Դ��
 */
class Output implements Runnable{
	Res res;
	public Output(Res res) {//���췽��ʱ������Ҫ��Դ��
		this.res=res;
	}
	@Override
	public void run() {
		while(true){
			System.out.println(Thread.currentThread().getName()+"  "+res.name+"  "+res.sex);
		}
	
		
	}
}