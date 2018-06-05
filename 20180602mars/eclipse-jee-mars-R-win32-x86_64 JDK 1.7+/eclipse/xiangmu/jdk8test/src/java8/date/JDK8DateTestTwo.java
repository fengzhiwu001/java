package java8.date;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.Period;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;

import org.junit.Test;

public class JDK8DateTestTwo {
	
	/**
	 * 1. LocalDate LocalDate(Java8)对象只包含没有任何时间信息的日期。  是一个表示日期 
	 * LocalTime  表示一整天中某个时间点的类，它的时间是无时区属性的（早上10点等等）。
	 * LocalDateTime   表示当地的日期与时间的类
	 */
	@Test
	public void test1(){
		LocalDate localDate = LocalDate.now();
		System.out.println(localDate);//2018-05-30
		LocalTime localTime = LocalTime.now();
		System.out.println(localTime);//10:20:05.560            理解 10点20分05秒 560纳秒
		LocalDateTime localDateTime = LocalDateTime.now();
		System.out.println(localDateTime);//2018-05-30T10:23:31.566   个人理解像是前边两个的整合
		
	}

	/**
	 * LocalDateTime的使用  可以连续的点击
	 */
	@Test
	public void test2(){
		LocalDateTime localDateTime = LocalDateTime.of(2018, 5, 25, 12, 30);//设置一个时间点
		System.out.println(localDateTime);//localDateTime
		LocalDateTime localDateTime1 = localDateTime.plusMinutes(20);//加20分钟
		System.out.println(localDateTime1);
		LocalDateTime localDateTime2 = localDateTime1.minusMinutes(10);//减10分钟
		System.out.println(localDateTime2);
		System.out.println(localDateTime2.getMonthValue());
		System.out.println(localDateTime2.getDayOfYear());
		System.out.println(localDateTime2.getYear());
	}
	
	/**
	 * Instant : 时间戳。 （使用 Unix 元年  1970年1月1日 00:00:00 所经历的毫秒值）
	 */
	@Test
	public void test3(){
		Instant instant = Instant.now();
		System.out.println(instant);//2018-05-30T02:34:50Z
		OffsetDateTime atOffset = instant.atOffset(ZoneOffset.ofHours(8));
		System.out.println(atOffset);//2018-05-30T10:41:08.889+08:00  区别是t后边的时间小时变成10，就是现在的时间
        System.out.println(atOffset.getNano());//得到纳秒
		System.out.println(instant.ofEpochSecond(5));//1970-01-01T00:00:05Z  从元年向前移动5秒
	}
	
	/**
	 * Duration : 用于计算两个“时间”间隔
	 *Period : 用于计算两个“日期”间隔
	 * @throws InterruptedException 
	 *
	 */
	@Test
	public void test4() throws InterruptedException{
		Instant instant1 =Instant.now();
		Thread.sleep(6000);
		Instant instant2 =Instant.now();
		Duration duration = Duration.between(instant1, instant2);//设置两个时间点  计算这两个时间点的间隔
		System.out.println(duration.getSeconds());
		
		LocalDate localDate1 = LocalDate.now();
		LocalDate localDate2 = LocalDate.of(2018, 5, 15);
		Period period = Period.between(localDate1, localDate2);
		System.out.println(period.getDays());//得到两个相差的天数
		System.out.println(period.getMonths());//得到两个相差的月数
	}
	
	/**
	 *  TemporalAdjuster : 时间校正器
	 */
	@Test
	public void test5(){
		LocalDateTime ldTime=LocalDateTime.now();
		LocalDateTime ldt2 = ldTime.withDayOfMonth(10);//withDayOfMonth方法的作用是把这个时间设置成 当月的设置的那一天
		System.out.println(ldt2);//2018-05-10T11:09:11.406
		LocalDateTime ldt3 = ldt2.with(TemporalAdjusters.firstDayOfMonth());
		//TemporalAdjusters.firstDayOfMonth() 设置成当前月的第一天
		System.out.println(ldt3);
	}
	
	/**
	 * DateTimeFormatter  设置转换方式
	 */
	@Test
	public void test6(){
		DateTimeFormatter dateTimeFormatter =DateTimeFormatter.ofPattern("yyyyMMdd");
		LocalDate localDate =LocalDate.now();
		System.out.println(localDate);
		String str = dateTimeFormatter.format(localDate);
		System.out.println(str);
	}
	
}
