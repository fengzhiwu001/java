package java8;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Test;

import java8.Employee.Status;


public class StreamTest3 {

	List<Employee> emps = Arrays.asList(
			new Employee(102, "李四", 79, 6666.66, Status.BUSY),
			new Employee(101, "张三", 18, 9999.99, Status.FREE),
			new Employee(103, "王五", 28, 3333.33, Status.VOCATION),
			new Employee(104, "赵六", 8, 7777.77, Status.BUSY),
			new Employee(104, "赵六", 8, 7777.77, Status.FREE),
			new Employee(104, "赵六", 8, 7777.77, Status.FREE),
			new Employee(105, "田七", 38, 5555.55, Status.BUSY)
	);

	//3. 终止操作
	/*
		归约
		reduce(T identity, BinaryOperator) / reduce(BinaryOperator) ——可以将流中元素反复结合起来，得到一个值。
	 */
	
	/**
	 * 求和
	 */
	@Test
	public void test(){
        List<Integer> list = Arrays.asList(1,2,3,4,5,6,7,8,9,10); 
        Integer sum = list.stream()
        .reduce(0,(x,y) ->x+y);
        //这个理解是 把值的sum默认等于0，然后把这个值指向x,y的值是集合的遍历的每一个值，然后这两个值进行想加，然后把求的和赋值给x,
        //然后再把y的值，一次往后传递，再和x的上次的求和想加，然后总和再次赋值给x,y值继续往后推
        System.out.println(sum);
	}
	
	/**
	 * 求员工薪水的综合
	 */
	@Test
	public void test2(){
		Optional<Double> optional = emps.stream()
		.map(Employee::getSalary)
		.reduce(Double::sum);
		System.out.println("总和   "+optional.get());
	}
	
	//上边两个方法 ，返回的值的类型不一样的原因是   第一个的值有一个默认的初始化的值，0.第二个没有这个默认的值
	
	/**
	 * collect——将流转换为其他形式。接收一个 Collector接口的实现，用于给Stream中元素做汇总的方法
	 */
	
	/**
	 * 转换成list集合
	 */
	@Test
	public void test3(){
		List<String> list = emps.stream()
		 .map((e) ->e.getName())//用于需要对一个流中的值进行某种形式的转换  map方法的作用
		 .collect(Collectors.toList());//collect方法用于转换成其他形式，传入的值Collectors的相关的方法
		System.out.println(list);
	}
	
	@Test
	public void test4(){
		System.out.println("****************转换成set集合*********************");
		Set<String> sets = emps.stream()
		.map(Employee::getName)
		.collect(Collectors.toSet());
		sets.forEach(System.out::println);//遍历set集合的内容
		
		System.out.println("****************转换成HashSet集合使用的方法Collectors.toCollection()*********************");
		HashSet<String> hashSet = emps.stream()
		.map((e) ->e.getName())
		.collect(Collectors.toCollection(HashSet::new));
		hashSet.forEach(System.out::println);
		
	}
	
	/**
	 * 搜索名字中 “六” 出现的次数
	 */
	@Test
	public void test5(){
		 Optional<Integer> optional = emps.stream()
		 .map(Employee::getName)//得到所有名字的集合
		 .flatMap(StreamTest3::filterCharacter)//把这些名字转一个一个拼接开来
		 .map((ch) ->{
			 if(ch.equals('六')){//注意这个是字符，而不是字符串
				 return 1;
			 }else{
				 return 0;
			 }
		 })//进行匹配
		 .reduce(Integer::sum);
		 Integer total = optional.get();
		 System.out.println("查询出来的结果   "+total);
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
	
	/**
	 * 求和，最大，最小，平均，总数
	 */
	@Test
	public void test6(){
		System.out.println("*********Collectors.maxBy的用法**********************");
		Optional<Double> optional = emps.stream()
		.map((e) -> e.getSalary())
		.collect(Collectors.maxBy(Double::compareTo));
		System.out.println("查找最大的     "+optional.get());
		
		System.out.println("*********Collectors.minBy的用法**********************");
		Optional<Double> optional1 = emps.stream()
		.map((e) -> e.getSalary())
		.collect(Collectors.minBy(Double::compareTo));
		System.out.println("查找最小的     "+optional1);
		System.out.println("查找最小的     "+optional1.get());
		
		System.out.println("*********Collectors.summingDouble的用法**********************");
		Double total = emps.stream()
		.collect(Collectors.summingDouble(Employee::getSalary));
		System.out.println("总和   "+total);
		
		System.out.println("*********Collectors.averagingDouble的用法**********************");
		Double avage1 = emps.stream()
		.collect(Collectors.averagingDouble(Employee::getSalary));
		System.out.println("平均值    "+avage1);
		
		System.out.println("*********Collectors.counting的用法**********************");
		Long counts = emps.stream()
		.collect(Collectors.counting());
		System.out.println("集合的总数      "+counts);
	}
	
	/**
	 * 分组  Collectors.groupingBy 分组产生的结果是一个map的集合
	 */
	@Test
	public void test7(){
		Map<Status, List<Employee>> map = emps.stream()
		.collect(Collectors.groupingBy(Employee::getStatus));
		System.out.println(map);
	}
	
	/**
	 * 多级分组Collectors.groupingBy(Employee::getStatus,Collectors.groupingBy()  传入的值是俩个
	 */
	@Test
	public void test8(){
		Map<Status, Map<String, List<Employee>>> map = emps.stream()
		.collect(Collectors.groupingBy(Employee::getStatus,Collectors.groupingBy((e) ->{
			if(e.getAge()>=60){
				return "老年";
			}else{
				return "中年";
			}
		})));
		System.out.println(map);
		
	}
	
	/**
	 * 分区  Collectors.partitioningBy
	 */
    @Test	
	public void test9(){
	   Map<Boolean, List<Employee>> map =
			   emps.stream().
			   collect(Collectors.partitioningBy((e) -> e.getSalary() >=5000));	
	   System.out.println(map);
	   
	}
	
    /**
     * 拼接字符串 传入的三个参数 ，第一个是中间的值，第二和第三是左边和右边的值
     * 结果   ----李四,张三,王五,赵六,赵六,赵六,田七----
     */
    @Test    
    public void test10(){
    	String str = emps.stream().
    	map(Employee::getName)
    	.collect(Collectors.joining("," , "----", "----"));
    	System.out.println(str);
    }
    
    /**
     * Collectors.reducing用法
     */
    @Test
    public void test11(){
    	Optional<Double> optional = emps.stream()
    	.map(Employee::getSalary)
    	.collect(Collectors.reducing(Double::sum));
    	System.out.println("求和    "+optional);
    	System.out.println("求和    "+optional.get());
    }
}
