package java8.date;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 使用Threadlocal来解决时间多线程不安全的问题
 * @author Administrator
 *
 */
public class DataFormatThreadLocal {

	private static final  ThreadLocal<DateFormat> threadLocal =new ThreadLocal<DateFormat>(){
		@Override
		protected DateFormat initialValue() {
			//通过匿名内部类覆盖ThreadLocal的initialValue()方法，指定初始值  这个方法是ThreadLocal内部的方法
			return new SimpleDateFormat("yyyyMMdd");
		}
	};
	
	/**
	 * 这个方法是对外提供的方法，自己定义的方法
	 * @return
	 * @throws ParseException
	 */
	public static final Date zhuanhuan() throws ParseException{
		return threadLocal.get().parse("20180523");
	}
}
