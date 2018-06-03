package com.test;

import java.util.Optional;

import org.junit.Test;

/*
 * һ��Optional �����ࣺ���ھ��������ָ���쳣
 * 	Optional.of(T t) : ����һ�� Optional ʵ��
 * 	Optional.empty() : ����һ���յ� Optional ʵ��
 * 	Optional.ofNullable(T t):�� t ��Ϊ null,���� Optional ʵ��,���򴴽���ʵ��
 * 	isPresent() : �ж��Ƿ����ֵ
 * 	orElse(T t) :  ������ö������ֵ�����ظ�ֵ�����򷵻�t
 * 	orElseGet(Supplier s) :������ö������ֵ�����ظ�ֵ�����򷵻� s ��ȡ��ֵ
 * 	map(Function f): �����ֵ���䴦�������ش�����Optional�����򷵻� Optional.empty()
 * 	flatMap(Function mapper):�� map ���ƣ�Ҫ�󷵻�ֵ������Optional
 */
public class OptionalTest {

	@Test
	public void test1() {
		Optional<Employee> of = Optional.of(new Employee());
		System.out.println(of.get());
		// Employee [id=0, name=null, age=0, salary=0.0, status=null]
		// Optional<Object> of2 = Optional.of(null);//NullPointerException ����
	}

	@Test
	public void test2() {
		Optional<Employee> ofNullable = Optional.ofNullable(new Employee());
		System.out.println(ofNullable.get());
		// Employee [id=0, name=null, age=0, salary=0.0, status=null]
		Optional<Employee> ofNullable1 = Optional.ofNullable(null);// ���ᱨ��
		// System.out.println(ofNullable1.get());
		// java.util.NoSuchElementException: No value present �ᱨ��
	}
	// �ϱߵ������������ǣ��Ƿ����ֱ�Ӵ���null��of����ʱֱ�ӱ���
	
	@Test
	public void test3(){
		Optional<Object> ofNullable = Optional.ofNullable(null);
		//Optional<Object> ofNullable = Optional.ofNullable(new Employee());
		if(ofNullable.isPresent()){
			System.out.println("1111");
			System.out.println(ofNullable.get());
		}
		//�����null���򲻻�ִ��if�жϵ����ݡ������new Employee()���ִ���±ߵķ�����
		Object obj = ofNullable.orElse(new Employee("zhangsan"));
		System.out.println(obj);
		//orElse�����������ǣ������null,���ִ���������
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
		//�����null���򲻻�ִ��if�жϵ����ݡ������new Employee()���ִ���±ߵķ�����
		Object obj = ofNullable.orElseGet(() ->new Employee("zhangsan  orElseGet"));
		System.out.println(obj);
		//orElse�����������ǣ������null,���ִ���������
		//Employee [id=0, name=zhangsan  orElseGet, age=0, salary=0.0, status=null]
		
	}
	//orElse����orElseGet������������ϱߵ�ֵ��Ϊ�յĻ������ִ���������
	
	/**
	 * map������ʹ��
	 */
	@Test
	public void test5(){
		Optional<Employee> op = Optional.of(new Employee(101, "����", 18, 9999.99));
		Optional<String> map = op.map((e) -> e.getName());
		System.out.println(map.get());//����
	}
	
	/**
	 * flatMap������ʹ��  ��map�ķ����������Ǵ��������ֵ
	 */
	@Test
	public void test6(){
		Optional<Employee> op = Optional.of(new Employee(101, "����", 18, 9999.99));
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
	//���󣺻�ȡһ����������Ů�������
	public String getGodnessName(Man man){
		if(man != null){
			Godness g = man.getGod();
			
			if(g != null){
				return g.getName();
			}
		}
		
		return "����ʦ";
	}
	
	/**
	 * ��Ҫ�����ֵ��Ҫ��Optional
	 * @param man
	 * @return
	 */
	public String getGodName(Optional<NewMan> man){//�жϴ����ֵ�����Ϊ�գ�����ִ���±ߵĴ���
		return man.orElse(new NewMan())//���Ϊ�գ��򴴽�һ������
		.getGodness()//�õ�God����
		.orElse(new Godness("����ʦ"))//���ߴ���һ������,�����ṩһ��Ĭ�ϵ�ֵ
		.getName();
	}
}
