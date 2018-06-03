package java8;

/**
 *接口式函数的定义 <br>
 *一、必须是接口interface<br>
 *二、必须只有一个方法，如果有多个方法，会提示报错<br>
 */
@FunctionalInterface
public interface Myopt {
	public int getValue(Integer integer);
}
