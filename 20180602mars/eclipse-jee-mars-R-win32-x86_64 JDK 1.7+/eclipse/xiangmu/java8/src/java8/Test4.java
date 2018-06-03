package java8;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Comparator;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.Consumer;
import java.util.function.Function;

import org.junit.Test;

public class Test4 {

	/**
	 * System.out::println  对象的引用，实例方法
	 */
	@Test
	public void test() {
		Consumer<String> consumer = (x) -> System.out.println("hello," + x); // (x)这个就是指的是传入的值
		consumer.accept("zhangsan");
		System.out.println("-------------------------------------");
		Consumer<String> consumer1 = System.out::println;
		consumer1.accept("zhangsan");
	}

	/**
	 * Integer::compare     对象的引用，实例方法
	 */
	@Test
	public void test1() {
      Comparator<Integer> comparator =(x,y) -> Integer.compare(x, y);
      int re = comparator.compare(1, 2);
      System.out.println(re);
      System.out.println("--------------------------------------");
      Comparator<Integer> comparator2 =Integer::compare;
      int res = comparator2.compare(1, 2);
      System.out.println(res);
	}
	
	
	/**
	 * Math::max  类名::静态方法
	 */
	@Test
	public void test3(){
		BiFunction<Double, Double, Double> biFunction =(x,y) ->Math.max(x,y);//传入两个值，返回一个值
		Double re = biFunction.apply(1.1,3.12);
		System.out.println(re);//返回最大的值
		System.out.println("**************************");
		BiFunction<Double, Double, Double> biFunction1 =Math::max;
		Double res = biFunction.apply(1.1,3.12);
		System.out.println(res);//返回最大的值
	}
	
	/**
	 * String::endsWith  对象::实例方法
	 */
	@Test
	public void test4(){
		BiPredicate<String, String> biPredicate= (x,y) ->x.equals(y); 
		boolean b = biPredicate.test("aa", "bb");
		System.out.println(b);
		System.out.println("**************************");
		BiPredicate<String, String> biPredicate1=String::endsWith;
		boolean b1 = biPredicate.test("aa", "bb");
		System.out.println(b1);
	}
	
	/***
	 * 数组的构造
	 */
	@Test
	public void test5(){
		Function<Integer, String[]> function =(args) ->new String[args];
		String[] strs = function.apply(10);
		System.out.println(strs.length);
		System.out.println("********************************");
		Function<Integer, String[]> function1 =String[]::new;
		String[] strs1 = function1.apply(10);
		System.out.println(strs1.length);
	}
	
	
}

