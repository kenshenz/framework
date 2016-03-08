package com.ksn.core.utils;

import java.util.Date;

import org.junit.Test;

public class DateTimeUtilsTest {

	@Test
	public void test1() {
		
		String date2string = DateTimeUtils.date2string(null);
		
		System.out.println(date2string);
		
	}
	
	@Test
	public void test2() {
		Date string2date = DateTimeUtils.string2date("2016-04-07");
		
		System.out.println(string2date);
	}
	
	@Test
	public void test3() {
		System.out.println(DateTimeUtils.isAM(new Date()));
	}
	
	@Test
	public void test4() {
		Date start = DateTimeUtils.string2date("2016-03-07");
		Date end = DateTimeUtils.string2date("2016-04-07");
		System.out.println(DateTimeUtils.getIntervalOfDay(end, start));
		
	}

}
