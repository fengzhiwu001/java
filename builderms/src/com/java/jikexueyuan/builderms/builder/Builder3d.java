package com.java.jikexueyuan.builderms.builder;

import com.java.jikexueyuan.builderms.vacation.Vacation;

/**
 *3天度假计划
 */
public class Builder3d extends AbsBuilder {

	/**
	 * 创建度假的构造器
	 */
	public Builder3d(String std) {
		super(std);

	}

	@Override
	public void buildDay(int i) {
		mVacation.setVacationDay(i);

	}

	/**
	 * 添加旅馆
	 */
	@Override
	public void addHotel(String hotel) {
		mVacation.setHotel(hotel);
	}
	/**
	 * 添加票
	 */
	@Override
	public void addTicket(String ticket) {
		mVacation.addTicket(ticket);
	}

	/**
	 * 添加活动
	 */
	@Override
	public void addEvent(String event) {
		mVacation.addEvent(event);
	}

	/**
	 * 创建度假计划
	 */
	@Override
	public void buildvacation() {
		//初始化的时候已经添加一天
		addTicket("Plane Ticket");
		addEvent("Fly to Destination");
		addEvent("Supper");
		addEvent("Dancing");
		addHotel("Four Seasons");

		mVacation.addDay();
		addTicket("Theme Park");
		addEvent("Bus to Park");
		addEvent("lunch");
		addHotel("Four Seasons");

		mVacation.addDay();

		addTicket("Plane Ticket");
		addEvent("City Tour");
		addEvent("Fly to Home");

	}

}
