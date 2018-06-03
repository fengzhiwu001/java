package com.test;

import java.util.Optional;

import org.junit.Test;

/*
 * 一、Optional 容器类：用于尽量避免空指针异常
 * 	Optional.of(T t) : 创建一个 Optional 实例
 * 	Optional.empty() : 创建一个空的 Optional 实例
 * 	Optional.ofNullable(T t):若 t 不为 null,创建 Optional 实例,否则创建空实例
 * 	isPresent() : 判断是否包含值
 * 	orElse(T t) :  如果调用对象包含值，返回该值，否则返回t
 * 	orElseGet(Supplier s) :如果调用对象包含值，返回该值，否则返回 s 获取的值
 * 	map(Function f): 如果有值对其处理，并返回处理后的Optional，否则返回 Optional.empty()
 * 	flatMap(Function mapper):与 map 类似，要求返回值必须是Optional
 */
public class OptionalTest {

	@Test
	public void test1() {
		Optional<Employee> of = Optional.of(new Employee());
		System.out.println(of.get());
		// Employee [id=0, name=null, age=0, salary=0.0, status=null]
		// Optional<Object> of2 = Optional.of(null);//NullPointerException 报错
	}

	@Test
	public void test2() {
		Optional<Employee> ofNullable = Optional.ofNullable(new Employee());
		System.out.println(ofNullable.get());
		// Employee [id=0, name=null, age=0, salary=0.0, status=null]
		Optional<Employee> ofNullable1 = Optional.ofNullable(null);// 不会报错
		// System.out.println(ofNullable1.get());
		// java.util.NoSuchElementException: No value present 会报错
	}
	// 上边的两个的区别是，是否可以直接传入null。of传入时直接报错。
	
	@Test
	public void test3(){
		Optional<Object> ofNullable = Optional.ofNullable(null);
		//Optional<Object> ofNullable = Optional.ofNullable(new Employee());
		if(ofNullable.isPresent()){
			System.out.println("1111");
			System.out.println(ofNullable.get());
		}
		//如果是null，则不会执行if判断的依据。如果是new Employee()则会执行下边的方法。
		Object obj = ofNullable.orElse(new Employee("zhangsan"));
		System.out.println(obj);
		//orElse方法表明的是，如果是null,则会执行这个方法
		//Employee [id=0, name=zhangsan, age=0, salary=0.0, status=null]
		
	}
	
	@Test
	public void test4(){
		Optional<Object> ofNullable = Optional.ofNullable(null);
		//Optional<Object> ofNullable = Optional.ofNullable(new Employee());
		if(ofNullable.isPresent()){
			System.out.println("1111");
			System.out.println(ofNullable.get());
		}
		//如果是null，则不会执行if判断的依据。如果是new Employee()则会执行下边的方法。
		Object obj = ofNullable.orElseGet(() ->new Employee("zhangsan  orElseGet"));
		System.out.println(obj);
		//orElse方法表明的是，如果是null,则会执行这个方法
		//Employee [id=0, name=zhangsan  orElseGet, age=0, salary=0.0, status=null]
		
	}
	//orElse或者orElseGet的作用是如果上边的值，为空的话，则会执行这个方法
	
	/**
	 * map方法的使用
	 */
	@Test
	public void test5(){
		Optional<Employee> op = Optional.of(new Employee(101, "张三", 18, 9999.99));
		Optional<String> map = op.map((e) -> e.getName());
		System.out.println(map.get());//张三
	}
	
	/**
	 * flatMap方法的使用  和map的方法的区别是传入参数的值
	 */
	@Test
	public void test6(){
		Optional<Employee> op = Optional.of(new Employee(101, "张三", 18, 9999.99));
		Optional<String> flatMap = op.flatMap((e) -> Optional.ofNullable(e.getName()));
	    System.out.println(flatMap.get());
	}

	@Test
	public void test7(){
		/*String godnessName = getGodnessName(new Man());
		System.out.println(godnessName);*/
		//String godName = getGodName(Optional.ofNullable(new NewMan()));
		String godName = getGodName(Optional.ofNullable(null));
		System.out.println(godName);
	}
	//需求：获取一个男人心中女神的名字
	public String getGodnessName(Man man){
		if(man != null){
			Godness g = man.getGod();
			
			if(g != null){
				return g.getName();
			}
		}
		
		return "苍老师";
	}
	
	/**
	 * 需要传入的值需要是Optional
	 * @param man
	 * @return
	 */
	public String getGodName(Optional<NewMan> man){//判断传入的值，如果为空，可以执行下边的代码
		return man.orElse(new NewMan())//如果为空，则创建一个对象
		.getGodness()//得到God对象
		.orElse(new Godness("苍老师"))//或者创建一个对象,并且提供一个默认的值
		.getName();
	}
}
