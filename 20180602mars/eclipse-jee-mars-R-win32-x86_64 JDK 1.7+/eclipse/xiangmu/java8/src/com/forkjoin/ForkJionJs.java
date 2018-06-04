package com.forkjoin;

import java.util.concurrent.RecursiveTask;

/**
 * RecursiveAction代表没有返回值的任务。
 *  RecursiveTask代表有返回值的任务。
 *  计算开始和结束的值
 */
@SuppressWarnings("rawtypes")
public class ForkJionJs extends RecursiveTask<Long> {
	//<Long>需要定义这个类型，否则会报错，在返回值的时候

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private long start;
	private long end;
	private static final long THRESHOLD = 10000L; // 临界值

	public ForkJionJs(long start, long end) {
		this.start = start;
		this.end = end;
	}

	@Override
	protected Long compute() {
		long len = end - start;
		if (len < 10000) {// 如果开始和结束小于中间值，则不用拆分
			long sum = 0;
			for (long i = 0; i < THRESHOLD; i++) {
				sum += sum + i;
			}
			return sum;
		} else {// 进行拆分
			
			  long middle=(start+len)/2;//从中间开始进行拆分
			  ForkJionJs left =new ForkJionJs(start, middle); 
			  left.fork();//前半部分进行拆分
			  ForkJionJs right =new ForkJionJs(middle+1,end);
			  right.fork();
			 return left.join()+right.join();//得到所有的计算的值
			
		}

	}

}
