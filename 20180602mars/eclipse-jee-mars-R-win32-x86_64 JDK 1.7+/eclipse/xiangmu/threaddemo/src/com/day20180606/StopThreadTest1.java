package com.day20180606;

/**
Java提供的终止方法只有一个stop，但是 不建议使用这个方法，<br>
stop方法是过时的<br>
从Java编码规则来说，已经过时的方法不建议采用。<br>
stop方法会导致代码逻辑不完整<br>
<br>
Java中多线程锁释放的条件：<br>
1）执行完同步代码块，就会释放锁。（synchronized）<br>
2）在执行同步代码块的过程中，遇到异常而导致线程终止，锁也会被释放。（exception）<br>
3）在执行同步代码块的过程中，执行了锁所属对象的wait()方法，这个线程会释放锁，进入对象的等待池。(wait)<br>
从上面的三点我就可以看到stop方法释放锁是在第二点的，通过抛出异常来释放锁，通过证明，这种方式是不安全的，不可靠的。<br>
 */
public class StopThreadTest1 {
	public static void main(String[] args) throws InterruptedException {
		
		Thread thread=new Thread(){
			 
			@Override
			public void run() {
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				
				System.out.println("停100后执行的代码，如果调用stop方法则不会执行");
			}
		};
		
		thread.start();
		Thread.sleep(100);
		thread.stop();
	}

}


/*如何正确停止线程
关于如何正确停止线程，这篇文章(how to stop thread)给出了一个很好的答案, 总结起来就下面3点（在停止线程时）：

1. 使用violate boolean变量来标识线程是否停止

2. 停止线程时，需要调用停止线程的interrupt()方法，因为线程有可能在wait()或sleep(), 提高停止线程的即时性

3. 对于blocking IO的处理，尽量使用InterruptibleChannel来代替blocking IO*/