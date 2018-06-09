package com.forkjoin;

import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.stream.LongStream;
import org.junit.Test;

public class TestForkJoin {

	/**
	 * 这个是使用forkjoin的方法
	 */
	@Test
	public void test1() {
		Instant start = Instant.now();
		ForkJoinPool forkJoinPool = new ForkJoinPool();// ForkJoinPool是一种支持任务分解的线程池
		ForkJoinTask<Long> forkJoinTask = new ForkJoinCalculate(0L, 100000000L);// 要拆分的值
		Long sum = forkJoinPool.invoke(forkJoinTask);// 把任务添加进来
		Instant end = Instant.now();
		Duration duration = Duration.between(start, end);// 计算俩个时间点的间隔差
		System.out.println(duration.toMillis());// 166
	}

	/**
	 * 这个是普通的相加的方法
	 */
	@Test
	public void test2() {
		Instant start = Instant.now();
		long sum = 0;
		for (long i = 0; i < 100000000L; i++) {
			sum += i;
		}
		Instant end = Instant.now();
		Duration duration = Duration.between(start, end);
		System.out.println(duration.toMillis());// 262
	}
	
	/**
	 *  range，需要传入开始节点和结束节点两个参数，返回的是一个有序的LongStream。包含开始节点和结束节点两个参数之间所有的参数，间隔为1.
     *    rangeClosed的功能和range类似。差别就是rangeClosed包含最后的结束节点，range不包含。
	 */
	
	
	@Test
	public void test3(){
		Instant start = Instant.now();
		long sum = LongStream.range(0L, 100000000L)
		.parallel()//转换成并行流
		.sum();
		Instant end = Instant.now();
		Duration duration = Duration.between(start, end);// 计算俩个时间点的间隔差
		System.out.println(duration.toMillis());
	}
	
}



/**
 * ForkJoinPool是一种支持任务分解的线程池，当提交给他的任务“过大”，他就会按照预先定义的规则将大任务分解成小任务，多线程并发执行。
 * 一般要配合可分解任务接口ForkJoinTask来使用，ForkJoinTask有两个实现它的抽象类：
 * RecursiveAction和RecursiveTask， 其区别是前者没有返回值，后者有返回值。
 */
