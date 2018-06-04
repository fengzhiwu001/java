package com.java.jikexueyuan.builderms.vacation;

import java.util.ArrayList;
import java.util.Date;

/**
 *度假每一天
 */
public class VacationDay {
	private Date mDate;//具体哪一天
	private String mHotels;//居住的酒店
	private ArrayList<String> mTickets = null;//具体的哪些票
	private ArrayList<String> mEvents = null;//具体的哪些事情

	public VacationDay(Date date) {//初始化
		mDate = date;
		mTickets = new ArrayList<String>();
		mEvents = new ArrayList<String>();
	}

	public void setDate(Date date) {
		mDate = date;
	}

	public void setHotel(String mHotels) {
		this.mHotels = mHotels;
	}

	public void addTicket(String ticket) {
		mTickets.add(ticket);
	}

	public void addEvent(String event) {
		mEvents.add(event);
	}

	/**
	 * 展示当天的活动
	 * @return
	 */
	public String showInfo() {
		StringBuilder stb = new StringBuilder();
		stb.append("Date:" + mDate.toString() + "\n");
		stb.append("Hotel:" + mHotels + "\n");
		stb.append("Tickets:" + mTickets.toString() + "\n");
		stb.append("Events" + mEvents.toString() + "\n");

		return stb.toString();
	}
}
