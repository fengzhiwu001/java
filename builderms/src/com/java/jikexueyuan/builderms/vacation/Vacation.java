package com.java.jikexueyuan.builderms.vacation;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
/**
 *度假计划
 */
public class Vacation {
	private ArrayList<VacationDay> mVacationDayLst;//这个假期有多长时间
	private Date mStDate;//开始时间
	private int mDays = 0;//一共多长时间
	private VacationDay mVacationDay;//这个作用是为了改动做的准备

	/**
	 * 初始化时，初始化一个日期
	 * @param std
	 */
	public Vacation(String std) {
		mVacationDayLst = new ArrayList<VacationDay>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			mStDate = sdf.parse(std);
			mVacationDay = new VacationDay(mStDate);
			mVacationDayLst.add(mVacationDay);//把设置的时间添加进来
			mDays++;//天数加1
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 设置日期
	 */
	public void setStDate(String std) {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			mStDate = sdf.parse(std);
		} catch (ParseException e) {
			e.printStackTrace();
		}

	}

	public Date getStDate() {

		return mStDate;
	}

	/**
	 * 添加天数
	 */
	public void addDay() {

		mVacationDay = new VacationDay(nextDate(mDays));//设置添加几天作为假期
		mVacationDayLst.add(mVacationDay);//把这个天数放到一个集合中
		mDays++;
	}

	/**
	 * 修改某一天的内容
	 * @param i
	 * @return
	 */
	public boolean setVacationDay(int i) {
		if ((i > 0) && (i < mVacationDayLst.size())) {
			mVacationDay = mVacationDayLst.get(i);
			return true;
		}
		mVacationDay = null;
		return false;
	}

	public void setHotel(String mHotels) {
		mVacationDay.setHotel(mHotels);
	}

	public void addTicket(String ticket) {
		mVacationDay.addTicket(ticket);
	}

	public void addEvent(String event) {
		mVacationDay.addEvent(event);
	}

	public void showInfo() {
		for (int i = 0, len = mVacationDayLst.size(); i < len; i++) {
			System.out.println("** " + (i + 1) + " day**");
			System.out.println(mVacationDayLst.get(i).showInfo());

		}
	}

	/**
	 * 设置可以游玩几天，传入的值就是设置的天数
	 * @param n
	 * @return
	 */
	private Date nextDate(int n) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(mStDate);
		cal.add(Calendar.DATE, n);
		return cal.getTime();
	}
}
