package com.day20180606;
/**  
* 一个简单的死锁类  网上搜的资料 <br>
* 当DeadLock类的对象flag==1时（td1），先锁定o1,睡眠500毫秒  <br>
* 而td1在睡眠的时候另一个flag==0的对象（td2）线程启动，先锁定o2,睡眠500毫秒  <br>
* td1睡眠结束后需要锁定o2才能继续执行，而此时o2已被td2锁定；  <br>
* td2睡眠结束后需要锁定o1才能继续执行，而此时o1已被td1锁定；  <br>
* td1、td2相互等待，都需要得到对方锁定的资源才能继续执行，从而死锁。  <br>
*/    
public class DeadLock implements Runnable {    
    public int flag = 1;    
    //静态对象是类的所有对象共享的    
    private static Object o1 = new Object(), o2 = new Object();    
    @Override    
    public void run() {    
        System.out.println("flag=" + flag);    
        if (flag == 1) {    
            synchronized (o1) {    
                try {    
                    Thread.sleep(500);    
                } catch (Exception e) {    
                    e.printStackTrace();    
                }    
                synchronized (o2) {    
                    System.out.println("1");    
                }    
            }    
        }    
        if (flag == 0) {    
        	System.out.println("flag="+flag);
            synchronized (o2) {    
                try {    
                    Thread.sleep(500);    
                } catch (Exception e) {    
                    e.printStackTrace();    
                }    
                synchronized (o1) {    
                    System.out.println("0");    
                }    
            }    
        }    
    }    
    
    public static void main(String[] args) {    
            
        DeadLock td1 = new DeadLock();    
        DeadLock td2 = new DeadLock();    
        td1.flag = 1;    
        td2.flag = 0;    
        //td1,td2都处于可执行状态，但JVM线程调度先执行哪个线程是不确定的。    
        //td2的run()可能在td1的run()之前运行    
        new Thread(td1).start();    
        new Thread(td2).start();    
    
    }    
}    
