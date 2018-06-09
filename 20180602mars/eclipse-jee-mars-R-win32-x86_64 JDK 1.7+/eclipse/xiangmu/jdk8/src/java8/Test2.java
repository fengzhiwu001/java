package java8;

import java.util.Comparator;
import java.util.function.Function;

import org.junit.Test;

public class Test2 {

	/**
	 * 有输入和输出的接口   
	 */
	@Test
	public void test(){
		Function<String, String> function =(x) -> x.toUpperCase();//x.toUpperCase()执行的表达式
		String result = function.apply("aaa");//传入的值    返回的值是输出的值
	    System.out.println(result);//输出返回的值
	}
	
	/**
	 * 转换大写的方法
	 * @param str  要转换的值
	 * @param function   传入的一个接口式函数
	 * @return
	 */
    public String toUpcase(String str,Function<String, String> function){
    	return function.apply(str);
    }
    
    /**
     * 把小写转换成大写
     */
    @Test
    public void test2(){
    	String upcase = toUpcase("aaab", (x)->x.toUpperCase());
    	System.out.println(upcase);
    }
    
    /**
     * Comparator 使用的是而不Comparable
     */
    @Test
	public void test3() {
		Comparator<Integer> comparator = new Comparator<Integer>() {

			@Override
			public int compare(Integer o1, Integer o2) {//调用比较的方法
				return Integer.compare(o1, o2);
			}
		};
		
		int reult = comparator.compare(1, 3);//如果前一个比后一个的值大，返回1 ，如果相等 0 。如果小于 -1
		System.out.println(reult);
	}
    
    /**
     * lambda表达式来实现两个值的比较
     */
    @Test
    public void test4(){
    	Comparator<Integer> comparator =(x,y) ->Integer.compare(x, y);
    	System.out.println(comparator.compare(1, 2));
    	
    	
    }
    
    
}
