package java8;

import java.util.Comparator;
import java.util.function.Consumer;

import org.junit.Test;

public class Test1 {

	@Test
	public void test1() {
		//runnable是多线程启动的一种方式
		Runnable runnable = new Runnable() {

			@Override
			public void run() {
				System.out.println("Runnable run....");
			}
		};
		runnable.run();//这个是执行run的方法
		
		Thread thread =new Thread(runnable);//被调用时，需要作为参数来进行传递。
		thread.start();//执行runnable中的run方法，这个是执行一个线程
	}
	
	@Test
	public void test2(){
	    //lambda表达式的使用 
		Runnable r1 = () -> System.out.println("hello  lambda!!!");
		 //调用run方法
		r1.run();
	}

	/**
	 * 传入一个带参数的值Consumer
	 */
    @Test	
	public void test3(){
		Consumer<String> consumer = (x) -> System.out.println("hello,  "+x);
		consumer.accept("xiaoli");
 	}
	
    /**
     * 如果传入的参数的值是一个，那么括号可以省略
     */
	@Test
    public void test4(){
    	Consumer<String> consumer =x ->System.out.println("hello,  "+x);
    	consumer.accept("xiaoli");
    }
	
	/**
	 * 传入的两个值，并且可以有一个返回的值，jdk8
	 */
	@Test
	public void test5(){
		Comparator<Integer> comp =(x,y) ->
		{
			System.out.println("这是执行的程序");
			return Integer.compare(x, y);
		};
		
		int result = comp.compare(2, 6);//调用执行的结果
		System.out.println("比较的结果:     "+result);
	}
	
	/**
	 * 接口式函数的定义和调用
	 */
	@Test
	public void test6(){
	   int result=	oprator(100, (x) -> x*x);
	   //(x) -> x*x的意思是传入的值是100，x*x指的是Myopt接口的实现，因为这个接口只是一个定义。这样的好处是可以省略代码的实现，可以使用简单的方法来进行实现。
	   System.out.println(result);
	   int res2 = oprator(1, (x) ->x+30);//这个的实现的方式是想加
	   System.out.println(res2);
	}
	
	public int oprator(Integer i,Myopt myOpt){
		return myOpt.getValue(i);
	}
	
}
