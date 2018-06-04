package com.java.jikexueyuan.builderms.builder;

import com.java.jikexueyuan.builderms.vacation.Vacation;
/**
 *自定义设置
 */
public class BuilderSelf {
	public Vacation mVacation;

	/**
    * @param std 传入一个度假计划	
	 */
	public BuilderSelf(String std) {
		mVacation = new Vacation(std);

	}

	public BuilderSelf addDay() {
		// TODO Auto-generated method stub

		mVacation.addDay();
		return this;//每一个返回的是自身，这样可以连续点击
	}

	public BuilderSelf buildDay(int i) {
		// TODO Auto-generated method stub

		mVacation.setVacationDay(i);
		return this;
	}

	public BuilderSelf addHotel(String hotel) {
		// TODO Auto-generated method stub
		mVacation.setHotel(hotel);
		return this;
	}

	public BuilderSelf addTicket(String ticket) {
		// TODO Auto-generated method stub
		mVacation.addTicket(ticket);
		return this;
	}

	public BuilderSelf addEvent(String event) {
		// TODO Auto-generated method stub
		mVacation.addEvent(event);
		return this;
	}

	public Vacation getVacation() {

		return mVacation;

	}
}
