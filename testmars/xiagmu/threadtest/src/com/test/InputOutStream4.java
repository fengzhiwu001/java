package com.test;

/**
 * InputOutStream3���Ż�
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
 * ������Դ����
 */
class Res4 {
	private String name;
	private String sex;
	private boolean flag = false;// �жϱ�־ ����Ĭ�ϼ�ʱִ����������

	public synchronized void set(String name, String sex) {
		if (flag) {// Ϊ�棬�ȴ������ִ��
			try {
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		} else {
			// ���Ϊ�٣�ִ�����룬������Ϻ������෴״̬�����������߳�
			this.name = name;
			this.sex = sex;
			flag = true;
			notify();
		}

	}

	public synchronized void out() {
		if (!flag) {// Ϊ�٣��ȴ�
			try {
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		} else {// ���Ϊ�棬ִ�������������������״̬������������
			System.out.println(Thread.currentThread().getName() + "  " + this.name + "  " + this.sex);
			flag = false;
			notify();
		}
	}

}

/**
 * д����
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
				res.set("����", "ŮŮŮ");
				flag = true;
			}
		}

	}
}

/**
 * ��ȡ��Դ��
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