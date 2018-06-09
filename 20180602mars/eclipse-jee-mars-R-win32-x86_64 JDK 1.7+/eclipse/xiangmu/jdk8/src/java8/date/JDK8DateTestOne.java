package java8.date;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import org.junit.Test;

/**
 * 这个类主要是显示jdk8版本时间相关类的线程安全和以前的时间类解决线程安全的方法
 * @author Administrator
 *
 */
public class JDK8DateTestOne {

	/**
	 * 创建一个固定大小的线程，调用 submit(Callable)，得到传入的值
	 */
	@Test
	public void test1(){
		SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyyMMdd");
		ExecutorService executorService = Executors.newFixedThreadPool(10);//创建一个固定大小的线程池
		
		/**
		 * submit(Callable)和submit(Runnable)类似，也会返回一个Future对象，但是除此之外，submit(Callable)接收的是一个Callable的实现，
		 * Callable接口中的call()方法有一个返回值，
		 * 可以返回任务的执行结果，而Runnable接口中的run()方法是void的，没有返回值
		 */
		Callable callable =new Callable() {//Callable接口类，是executorService的submit方法需要的

			@Override
			public Object call() throws Exception {
				return simpleDateFormat.parse("20180530");
			}
		};
		
		
		Future future = executorService.submit(callable);
		try {
			System.out.println(future.get());
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 时间对象多线程不安全
	 * 测试程序是否会报错，需要执行多次，其中就有可能是程序会有问题，但是多执行几次有的就没有问题。
	 * @throws InterruptedException
	 * @throws ExecutionException
	 */
	@Test
	public void test2() throws InterruptedException, ExecutionException{

		SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyyMMdd");
		ExecutorService executorService = Executors.newFixedThreadPool(10);//创建一个固定大小的线程池
		
		Callable callable =new Callable() {//Callable接口类，是executorService的submit方法需要的
			@Override
			public Object call() throws Exception {
				return simpleDateFormat.parse("20180530");
			}
		};
		
		List<Future>  list =new ArrayList<>();
		
		for(int i=0;i<10;i++){//遍历十次，存放到list集合中
			list.add(executorService.submit(callable));
		}
		
		for (Future future : list) {
			System.out.println(future.get());//得到每一个值
		}
		
		executorService.shutdown();//关闭线程
 		
		
	}
	
	/**
	 * 使用传统的方法ThreadLocal来实现时间的安全性
	 * @throws InterruptedException
	 * @throws ExecutionException
	 */
	@Test
	public void test3() throws InterruptedException, ExecutionException{

		ExecutorService executorService = Executors.newFixedThreadPool(10);
		
		Callable callable =new Callable() {
			@Override
			public Object call() throws Exception {
				return DataFormatThreadLocal.zhuanhuan();//只有这一行代码是跟以前不一样的，这是调用ThreadLocal的方法来进行实现
			}
		};
		
		List<Future>  list =new ArrayList<>();
		
		for(int i=0;i<10;i++){
			list.add(executorService.submit(callable));
		}
		
		for (Future future : list) {
			System.out.println(future.get());
		}
		
		executorService.shutdown();
 		
		
	}
	
	/**
	 * 使用jdk8的时间的相关类来解决线程安全的问题
	 *   DateTimeFormatter和LocalDate
	 * @throws InterruptedException
	 * @throws ExecutionException
	 */
	@Test
	public void test4() throws InterruptedException, ExecutionException{
		DateTimeFormatter  dateTimeFormatter =DateTimeFormatter.ofPattern("yyyyMMdd");//jdk8的类，设置显示格式
		
		ExecutorService executorService = Executors.newFixedThreadPool(10);
		Callable<LocalDate> callable =new Callable<LocalDate>() {

			@Override
			public LocalDate call() throws Exception {
				LocalDate localDate =LocalDate.parse("20180520", dateTimeFormatter);//jdk8的类
				return localDate;
			}
		};
		List<Future<LocalDate>> list =new ArrayList<Future<LocalDate>>();
		
		for(int i=0;i<10;i++){
			list.add(executorService.submit(callable));
		}
		
		for (Future<LocalDate> future : list) {
			System.out.println(future.get());
		}
		
		executorService.shutdown();
		
	}
	
	
}
