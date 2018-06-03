package java8;

import java.util.Optional;

import org.junit.Test;

public class OptionalTest {

	/**
	 * Optional.of方法得到的值，不管是否为空，结果不会是空指针,但是不能传入的值为null，否则会报错，空指针异常
	 */
	@Test
	public void test(){
		Optional<Employee> optional = Optional.of(new Employee());
		Employee employee = optional.get();
		System.out.println(employee.getName());
		System.out.println("*******************************");
		//Optional<Employee> optional1 = Optional.of(null);//这个会报错，空指针异常
		
	}
	
	/**
	 * Optional.ofNullable方法得到的值，不管是否为空，结果不会是空指针.这个不会出现上边的情况
	 */
	@Test
	public void test1(){
		Optional<Employee> optional = Optional.ofNullable(new Employee());
		Employee employee = optional.get();
		System.out.println(employee.getName());
		System.out.println("*******************************");
		Optional<Employee> optional1 = Optional.ofNullable(null);//不会报错
		//System.out.println(optional1.get());// 会报错 No value present  原因是null调用null会报错
	
	}
	/**
	 * orElse和orElseGet用法
	 */
	
	@Test
	public void test2(){
		Optional<Employee> optional = Optional.ofNullable(new Employee());
		if(optional.isPresent()){//返回结果是true
			Employee employee = optional.get();
		}
		
		Employee optional2 = optional.orElse(new Employee("zhangsan"));
		System.out.println(optional2);//Employee [id=0, name=null, age=0, salary=0.0, status=null]

		Employee optional3 = optional.orElseGet(() -> new Employee("lisi"));
		System.out.println(optional3);//Employee [id=0, name=null, age=0, salary=0.0, status=null]
	}
	
	/**
	 * map方法和flatMap方法的用法
	 */
	@Test
	public void test3(){
		Optional<Employee> op = Optional.of(new Employee(101, "张三", 18, 9999.99));
		Optional<String> map = op.map((e) ->e.getName());
		System.out.println(map);//Optional[张三]
		System.out.println(map.get());//张三
		System.out.println("******************");
		
		Optional<Employee> op1 = Optional.of(new Employee(101, "张三", 18, 9999.99));
		Optional<String> flatMap = op1.flatMap((e) -> Optional.of(e.getName()));
		System.out.println(flatMap);//Optional[张三]
		System.out.println(flatMap.get());//张三
	}
	
	
}
