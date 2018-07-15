package com.test;

/**
* �ȴ����ѻ���<br>
* ��Ϊ����ֻ�������̣߳��������һ���̴߳��ڵȴ�״̬�Ļ�����ô�����һ���߳̿϶��ᴦ��ִ��״̬����������߳�wait֮ǰ��
* ���뻽�������̣߳����򶼻ᴦ�ڵȴ�״̬��<br>
* ������ӱ�������һ���龰���������õ��ǣ������Դ����ı�־��false���������룬�����true�������������������������
* ״̬����������෴���Ա㽻��ִ������������Ŀ��
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
 *������Դ����
 */
class Res3{
	String name;
	String sex;
	boolean biaozhi=false;//�жϱ�־  ����Ĭ�ϼ�ʱִ����������
}

/**
 *д����
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
				if(res.biaozhi){//�����Դ��־����Ļ����ȴ�
					
					try {
						res.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					
				}else{//��־�ǼٵĻ���ִ��������Ϣ
					if(flag){
						res.name="mike";
						res.sex="man";
						flag=false;
					}else{
						res.name="����";
						res.sex="ŮŮŮ";
						flag=true;
					}
					
					//ִ����ϵĻ�����Ҫ�ѱ�־���ó��棬�����������������Ĳ�����������������ķ�����
					res.biaozhi=true;
					res.notify();//���ѵ��Ƕ��󣬶����Ǳ���
				}
					
				
				
			}
			
		}
	
		
	}
}

/**
 *��ȡ��Դ��
 */
class Output3 implements Runnable{
	Res3 res;
	public Output3(Res3 res) {
		this.res=res;
	}
	@Override
	public void run() {
		while(true){
			synchronized(res){//��Ϊ���Ķ����������Ҫ����ע��
				if(!res.biaozhi){
					//���������������������෴
					try {
						res.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					
				}else{
					System.out.println(Thread.currentThread().getName()+"  "+res.name+"  "+res.sex);
					
					res.biaozhi=false;
					res.notify();//���ñ�־��������������Ϣ�������룬���ҽ��Ѷ���
				}
				
			}
			
		}
	
		
	}
}