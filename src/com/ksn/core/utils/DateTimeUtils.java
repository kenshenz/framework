package com.ksn.core.utils;

import java.util.Calendar;
import java.util.Date;

import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.Minutes;
import org.joda.time.Months;
import org.joda.time.Seconds;
import org.joda.time.Years;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

/**
 * 日期工具类
 * 使用joda作为第三方日期库
 * @author ccf
 * @date 2016年3月7日
 *
 */
public class DateTimeUtils {
	
	public static String PATTERN_DATETIME = "yyyy-MM-dd HH:mm:ss";
	public static String PATTERN_DATE = "yyyy-MM-dd";
	public static String PATTERN_TIME = "HH:mm:ss";
	
	public static DateTimeFormatter FORMATTER_DATETIME = DateTimeFormat.forPattern(PATTERN_DATETIME);
	public static DateTimeFormatter FORMATTER_DATE = DateTimeFormat.forPattern(PATTERN_DATE);
	public static DateTimeFormatter FORMATTER_TIME = DateTimeFormat.forPattern(PATTERN_TIME);
	
	/**
	 * 日期转字符串
	 * 日期格式为yyyy-MM-dd
	 * @param date
	 * @return
	 */
	public static String date2string(Date date) {
		return new DateTime(date).toString(PATTERN_DATE);
	}
	
	/**
	 * 日期转字符串
	 * 日期格式为yyyy-MM-dd HH:mm:ss
	 * @param date
	 * @return
	 */
	public static String datetime2string(Date date) {
		return new DateTime(date).toString(PATTERN_DATETIME);
	}
	
	/**
	 * 字符串转日期
	 * 日期格式为yyyy-MM-dd
	 * @param str
	 * @return
	 */
	public static Date string2date(String str) {
		return DateTime.parse(str, FORMATTER_DATE).toDate();
	}
	
	/**
	 * 字符串转日期
	 * 日期格式为yyyy-MM-dd HH:mm:ss
	 * @param str
	 * @return
	 */
	public static Date string2datetime(String str) {
		return DateTime.parse(str, FORMATTER_DATETIME).toDate();
	}
	
	/**
	 * 比较日期大小
	 * date1 等于 date2, 返回 0
	 * date1 大于 date2, 返回 1
	 * date1 小于 date2, 返回 -1
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static int compare(Date date1, Date date2) {
		return new DateTime(date1).compareTo(new DateTime(date2));
	}
	
	/**
	 * 判断时间是否早上
	 * @param date
	 * @return
	 */
	public static boolean isAM(Date date) {
		return new DateTime(date).toCalendar(null).get(Calendar.AM_PM) == Calendar.AM;
	}
	
	/**
	 * 返回一天的开始时间
	 * @param date
	 * @return
	 */
	public static Date getFirstTime(Date date) {
		return new DateTime(date).withTime(0, 0, 0, 0).toDate();
	}
	
	/**
	 * 返回昨天的日期
	 * @param date
	 * @return
	 */
	public static Date getYesterday(Date date) {
		return new DateTime(date).plusDays(-1).toDate();
	}
	
	/**
	 * 返回明天的日期
	 * @param date
	 * @return
	 */
	public static Date getTomorrow(Date date) {
		return new DateTime(date).plusDays(1).toDate();
	}
	
	/**
	 * 计算两个日期相隔的年数
	 * start 等于 end, 返回0
	 * start 大于 end, 返回正数
	 * start 小于 end, 返回负数
	 * @param start
	 * @param end
	 * @return
	 */
	public static int getIntervalOfYear(Date start, Date end) {
		return Years.yearsBetween(new DateTime(start), new DateTime(end)).getYears();
	}
	
	/**
	 * 计算两个日期相隔的月数
	 * start 等于 end, 返回0
	 * start 大于 end, 返回正数
	 * start 小于 end, 返回负数
	 * @param start
	 * @param end
	 * @return
	 */
	public static int getIntervalOfMonth(Date start, Date end) {
		return Months.monthsBetween(new DateTime(start), new DateTime(end)).getMonths();
	}
	
	/**
	 * 计算两个日期相隔的天数
	 * start 等于 end, 返回0
	 * start 大于 end, 返回正数
	 * start 小于 end, 返回负数
	 * @param start
	 * @param end
	 * @return
	 */
	public static int getIntervalOfDay(Date start, Date end) {
		return Days.daysBetween(new DateTime(start), new DateTime(end)).getDays();
	}
	
	/**
	 * 计算两个日期相隔的分钟数
	 * start 等于 end, 返回0
	 * start 大于 end, 返回正数
	 * start 小于 end, 返回负数
	 * @param start
	 * @param end
	 * @return
	 */
	public static int getIntervalOfMinute(Date start, Date end) {
		return Minutes.minutesBetween(new DateTime(start), new DateTime(end)).getMinutes();
	}
	
	/**
	 * 计算两个日期相隔的秒数
	 * start 等于 end, 返回0
	 * start 大于 end, 返回正数
	 * start 小于 end, 返回负数
	 * @param start
	 * @param end
	 * @return
	 */
	public static int getIntervalOfSecond(Date start, Date end) {
		return Seconds.secondsBetween(new DateTime(start), new DateTime(end)).getSeconds();
	}
	
}
