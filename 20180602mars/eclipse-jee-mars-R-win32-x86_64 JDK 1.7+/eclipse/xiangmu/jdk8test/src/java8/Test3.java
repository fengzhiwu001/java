package java8;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

import org.junit.Test;

/**
 * java8的基本的一些重要的接口
 */
public class Test3 {
  
	/**
	 * 有输入没有输出
	 */
	@Test
	public void test1(){
		Consumer<String> consumer =(x) ->System.out.println("你好,"+x);
		consumer.accept("小李");
	}
	
	/**
	 * 有输出没有输入
	 */
    @Test	
	public void test2(){
		Supplier<String> supplier =() -> "i"; //-> 箭头后边的值，需要返回的是一个Supplier的参数类型的值
		String result = supplier.get();//使用对象得到返回的值
		System.out.println(result);
	}
    
    @Test	
  	public void test3(){
  		Supplier<String> supplier =() -> {
  			String str = "结果:";
  			int len =0;
  			for(int i=0;i<10;i++){
  				len=len+i;
  			}
  			return str+len;
  		};//大括号内的是多行  并且最终的返回值 也得是String类型
  		
  		String result = supplier.get();//使用对象得到返回的值
  		System.out.println(result);
  	}
    
    /**
     * 有输入值和输出值
     */
    @Test    
    public void test4(){
    	Function<String, String> function =(x) ->{
    		return x.toUpperCase();
    	};//x是输入参数，大括号是输出参数
    	
    	String result = function.apply("abvc");//调用一个方法  输入一个值 返回一个值
        System.out.println(result);
    }
    
    /***
     * Predicate 判断 判断传入的值和字符串是否相同 ，返回一个boolean的值   
     * test方法的使用
     */
    @Test
    public void test5(){
    	Predicate<String> predicate = (x) -> {
    		return "hello".equals(x);
    	} ;
    	
       boolean result=	predicate.test("he");
       System.out.println(result);
    }
    
    /**
     * isEqual方法的用法，这个方法是一个静态的方法，返回值是Predicate类。并且传入的参数可以和调用test方法来及进行参数的比较。
     */
    @Test
    public void test6(){
    	System.out.println(Predicate.isEqual("test").test("test"));
    }
    
    /**
     * 如果字符串的长度大于2,则添加
     * @return
     */
    @Test
    public void test7(){//方法如果有返回值的话，执行报错
    	List<String> list=Arrays.asList("aa","baba","abc","a","dhsg");
    	List<String>  result =new ArrayList<String>();
    	
     	Predicate<String> predicate = (x) ->{//这个是判断传入的字符串的长度是不是大于等于2，如果是的话，返回真
     		return x.length()-2>=0;
    	};
      
    	for (String str : list) {//遍历集合，如果，通过调用predicate的方法是boolean的值来进行判断
    		boolean flag = predicate.test(str);
    		if(flag){
    			result.add(str);
    		}
		}
    	
    	System.out.println(result);
    }
    
    
    
}
