package java8;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import org.junit.Test;

import java8.Employee.Status;

public class StreamTest2 {

	List<Employee> emps = Arrays.asList(
			new Employee(102, "李四", 59, 6666.66, java8.Employee.Status.BUSY),
			new Employee(101, "张三", 18, 9999.99, java8.Employee.Status.FREE),
			new Employee(103, "王五", 28, 3333.33, java8.Employee.Status.VOCATION),
			new Employee(104, "赵六", 8, 7777.77, java8.Employee.Status.BUSY),
			new Employee(104, "赵六", 8, 7777.77, java8.Employee.Status.FREE),
			new Employee(104, "赵六", 8, 7777.77, java8.Employee.Status.FREE),
			new Employee(105, "田七", 38, 5555.55, java8.Employee.Status.BUSY)
	);
	
	//3. 终止操作
		/*
			allMatch——检查是否匹配所有元素
			anyMatch——检查是否至少匹配一个元素
			noneMatch——检查是否没有匹配的元素
			findFirst——返回第一个元素
			findAny——返回当前流中的任意元素
			count——返回流中元素的总个数
			max——返回流中最大值
			min——返回流中最小值
		 */
	
	@Test
	 public void test1(){
		boolean b1=emps.stream()
		.allMatch((e) ->e.getStatus().equals(java8.Employee.Status.BUSY));
		System.out.println("emps集合中，状态是否全部匹配Status.BUSY  "+b1);
		System.out.println("**********************************************");
		
		boolean anyMatch = emps.stream()
		.anyMatch((e) ->e.getStatus().equals(java8.Employee.Status.BUSY));
		System.out.println("emps集合中，检查是否至少匹配一个元素Status.BUSY  "+anyMatch);
		System.out.println("**********************************************");
		
		boolean noneMatch = emps.stream()
		.noneMatch((e) ->e.getStatus().equals(Status.BUSY));
		System.out.println("emps集合中，检查是否没有匹配的元素 Status.BUSY  "+noneMatch);
		System.out.println("**********************************************");
		
		//下边代码的findFirst方法的作用是得到第一个元素，这个返回值是Optional，原因是解决控制针的
		//问题
		Optional<Employee> optional1 = emps.stream()
		.findFirst();
		System.out.println("emps集合中，返回第一个元素的名字  "+optional1.get().getName());
		System.out.println("**********************************************");
		
		//这个是排序后，根据薪水最低的一个查询出来最低的一个返回出来。
		Optional<Employee> optional2 = emps.stream()
		.sorted((e1,e2) -> Double.compare(e1.getSalary(), e2.getSalary()))
		.findFirst();
		System.out.println("emps集合中，返回第一个元素的名字  "+optional2.get());
		System.out.println("**********************************************");
		
		System.out.println("*******************findAny***************************");
		Optional<Employee> optional3 = emps.parallelStream()
		.findAny();
		System.out.println("findAny——返回当前流中的任意元素     "+optional3.get());
		System.out.println("**********************************************");
		
		Optional<Employee> optional4 = emps.parallelStream()
		.filter((e) ->e.getStatus().equals(Status.BUSY))
		.findAny();
		System.out.println("findAny——返回当前流中的任意元素     "+optional4.get());
		System.out.println("**********************************************"); 
		
		System.out.println("*******************count方法***************************");
		long count = emps.stream()
		.count();
		System.out.println("count——返回流中元素的总个数     "+count);
		System.out.println("**********************************************"); 
		
		System.out.println("*******************min***************************");
		Optional<Employee> min = emps.stream()
		.min((e1,e2) ->Double.compare(e1.getSalary(), e2.getSalary()));
		System.out.println("min——返回流中最小值     "+min.get());
		System.out.println("**********************************************");
		
		System.out.println("*******************max***************************");
		Optional<Employee> max = emps.stream()
				.max((e1,e2) ->Double.compare(e1.getSalary(), e2.getSalary()));
		System.out.println("min——返回流中最小值     "+max.get());
		System.out.println("**********************************************");
				
				Optional<Double> max2 = emps.stream()
				.map(Employee::getSalary)
				.max(Double::compareTo);
				System.out.println(max2.get());
	    System.out.println("**********************************************");				
		
	}
	
	/**
	 * 注意：流进行了终止操作后，不能再次使用
	 */
	@Test
	public void test2(){
		Stream<Employee> stream = emps.stream()//这个是得到所有状态是BUSY的一个集合
		.filter((e1) ->e1.getStatus().equals(Status.BUSY));
		
		boolean allMatch = stream.allMatch((e1) -> e1.getStatus().equals(Status.FREE));
		System.out.println("集合中的数据是否全部匹配     "+allMatch);
		
		long count = stream.count();//stream has already been operated upon or closed  程序会报错
		System.out.println("集合中的数据的个数      "+count);
		
		
	}
}
