package com.java.jikexueyuan.builderms;

import com.java.jikexueyuan.builderms.builder.AbsBuilder;

/**
 *指导者 
 */
public class Director {
	private AbsBuilder builder;//传入一个抽象的变量
	
	public Director(AbsBuilder builder)
	{
		this.builder=builder;
	}
	public void setBuilder(AbsBuilder builder)
	{
		this.builder=builder;
	}
	public void construct()
	{
		builder.buildvacation();//创建度假信息
		builder.getVacation().showInfo();//展示度假信息
	}
}
