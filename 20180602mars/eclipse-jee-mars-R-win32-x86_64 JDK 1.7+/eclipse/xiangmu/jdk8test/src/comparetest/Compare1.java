package comparetest;

import java.util.ArrayList;
import java.util.Collections;
import org.junit.Test;

/**
 * compare接口的测试
 *
 */
public class Compare1{  
	
	@Test//list集合的排序
	public void test1(){
		ArrayList<Integer> arr = new ArrayList<Integer>();  
        arr.add(10);  
        arr.add(23);  
        arr.add(7);  
        System.out.println(arr);  //ArrayList是先进先出型的
        Collections.sort(arr);  //排序
        System.out.println(arr);
	}
	
	@Test
	public void test2(){
	        ArrayList arr = new ArrayList();  
	        arr.add(new Student("Jack",10));  
	        arr.add(new Student("Bill",23));  
	        arr.add(new Student("Rudy",7));  
	        System.out.println(arr);  
	       //  Collections.sort(arr); //报错  
	        //java.lang.ClassCastException: com.test.comparetest.Student cannot be cast to java.lang.Comparable
	        System.out.println(arr);
	}
	
	@Test
	public void test3(){
	        ArrayList arr = new ArrayList();  
	        arr.add(new Student1("Jack",10));  
	        arr.add(new Student1("Bill",23));  
	        arr.add(new Student1("Rudy",7));  
	        System.out.println(arr);  
	         Collections.sort(arr); //这个不报错并且可以实现排序的原因是  这个Student1类实现了Compare接口
	        System.out.println(arr);
	}
	
	
	
} 

class Student{  
    private String name;  
    private int ranking;  
  
    public Student(String name, int ranking){  
        this.name = name;  
        this.ranking = ranking;  
    }   
  
    public String toString(){  
        return this.name + ":" + this.ranking;  
    }  
}  

class Student1 implements Comparable<Object>{  
    private String name;  
    private int ranking;  
  
    public Student1(String name, int ranking){  
        this.name = name;  
        this.ranking = ranking;  
    }   
  
    public String toString(){  
        return this.name + ":" + this.ranking;  
    }

	@Override
	public int compareTo(Object o) {
		Student1 s1=(Student1)o;
		return  this.ranking -s1.ranking;
	}  
}


  
