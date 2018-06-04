package com.java.jikexueyuan.builderms.builder;

import com.java.jikexueyuan.builderms.vacation.Vacation;

/**
 *抽象生成器
 */
public abstract class AbsBuilder {

	public Vacation mVacation;//传入一个度假计划

	public AbsBuilder(String std) {
		mVacation = new Vacation(std);
	}

	public abstract void buildvacation();

	public abstract void buildDay(int i);

	public abstract void addHotel(String hotel);

	public abstract void addTicket(String ticket);

	public abstract void addEvent(String tvent);

	public Vacation getVacation() {//返回给指导者

		return mVacation;

	}

}
