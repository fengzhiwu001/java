package java8;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/*
 * 一、 Stream 的操作步骤
 * 
 * 1. 创建 Stream
 * 
 * 2. 中间操作
 * 
 * 3. 终止操作
 */
import org.junit.Test;

public class StreamTest1 {

	/**
	 * 得到Stream对象的两种方法
	 */
	@Test
	public void test1() {
		// 1. Collection 提供了两个方法 stream() 与 parallelStream()
		List<String> list = new ArrayList<String>();
		Stream<String> stream = list.stream();
		// 2. 通过 Arrays 中的 stream() 获取一个数组流
		Integer[] ins = new Integer[10];
		Stream<Integer> stream2 = Arrays.stream(ins);
		// 3. 通过 Stream 类中静态方法 of()
		Stream<String> Stream3 = Stream.of("aa", "bb", "cc");
		// 4. 创建无限流 两种方式
		//第一种方式 迭代
		Stream.iterate(0, (x) ->x+2);
		//传入的值的含义  第一个要迭代的初始化的值，后边传入的是UnaryOperator<T> extends Function<T, T> 需要传入一个值，并且需要有返回值
		//Stream.iterate(0, (x) ->x+2).forEach(System.out::println);
		//上边的代码会一直的执行，无限的进行下去
		Stream.iterate(0, (x) ->x+2)
		.limit(10)
		.forEach(System.out::println);
        //上边的代码是把这个无限的值，进行了初始化，这样是显示10条，并且初始值是0  
		System.out.println("***********************************");
		//第二种方式   生成
		Stream.generate(()->Math.random());//不需要传入的值，但是需要返回值
	  //	Stream.generate(()->Math.random()).forEach(System.out::println);
		//上边的值也会一直的产生
		Stream.generate(()->Math.random())
		.limit(10)
		.forEach(System.out::println);
		//上边的代码是控制显示10条数据
	}
	
	//2. 中间操作
			List<Employee> emps = Arrays.asList(
					new Employee(102, "李四", 59, 6666.66),
					new Employee(101, "张三", 18, 9999.99),
					new Employee(103, "王五", 28, 3333.33),
					new Employee(104, "赵六", 8, 7777.77),
					new Employee(104, "赵六", 8, 7777.77),
					new Employee(104, "赵六", 8, 7777.77),
					new Employee(105, "田七", 38, 5555.55)
			);
			/*
			  筛选与切片
				filter——接收 Lambda ， 从流中排除某些元素。
				limit——截断流，使其元素不超过给定数量。
				skip(n) —— 跳过元素，返回一个扔掉了前 n 个元素的流。若流中元素不足 n 个，则返回一个空流。与 limit(n) 互补
				distinct——筛选，通过流所生成元素的 hashCode() 和 equals() 去除重复元素
				这些方法都是Stream对象的方法，需要得到一个stream的对象，然后进行相应的调用
			 */
	        
			/**
			 * 过滤测试filter
			 */
			@Test
			public void test2(){
				List<String> list = Arrays.asList("aa","bbb","cc","dd","eee");
				list.stream()
				.filter((x)->x.length()-2>0)
				.forEach(System.out::println);
			}
			
			/**
			 * 过滤查询前两条
			 */
			@Test
			public void test3(){
				emps.stream()
				.filter((e) -> e.getSalary()>5000)
				.limit(2)
				.forEach(System.out::println);;
				
			}
			
			/**
			 * 跳过前两条
			 */
			@Test
			public void test4(){
				emps.stream()
				.filter((e) -> e.getSalary()>5000)
				.skip(2)
				.forEach(System.out::println);;
				
			}
			
			/**
			 * 去除重复，需要重写hashcode和equals方法
			 */
			@Test
			public void test5(){
				emps.stream()
				.filter((e) -> e.getSalary()>5000)
				.distinct()
				.forEach(System.out::println);;
				
			}
			
			/*
			映射
			map——接收 Lambda ， 将元素转换成其他形式或提取信息。接收一个函数作为参数，该函数会被应用到每个元素上，并将其映射成一个新的元素。
			flatMap——接收一个函数作为参数，将流中的每个值都换成另一个流，然后把所有流连接成一个流
		 */
			
			@Test
			public void test6(){
				/**
				 * 把字母小写转换成大写
				 */
				List<String> list = Arrays.asList("aa","bb","cc","dd","ee");
				list.stream()
				.map((x) ->x.toUpperCase())
				.forEach(System.out::println);
				
				System.out.println("*************map方法的使用********************");
				
				Stream<Stream<Character>> stream1 = list.stream()
				.map(StreamTest1::filterCharacter);
				//解释  map()方法，返回的是Stream对象，并且StreamTest1::filterCharacter也是返回的是Stream<Character>，所以写法就是上边的返回的值
				stream1.forEach((sm) ->sm.forEach(System.out::println));//个人理解是两层循环所以循环两次
               
				System.out.println("**************flatMap方法的使用************************");
				
				 Stream<Character> flatMap = list.stream().flatMap(StreamTest1::filterCharacter);
				 flatMap.forEach(System.out::println);
				
			}
			
			/**
			 * 把字符串中的字符转换层Stream
			 * @return
			 */
			public static Stream<Character> filterCharacter(String str){
				List<Character> list=new ArrayList<Character>();
				for (Character ch : str.toCharArray()) {//字符串转换成字节数组进行转换
					list.add(ch);
				}
				return list.stream();
			}
			
			
	
}
