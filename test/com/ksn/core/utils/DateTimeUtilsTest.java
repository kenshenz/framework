package com.ksn.core.utils;

import java.util.Date;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DateTimeUtilsTest {
	Logger logger = LoggerFactory.getLogger(DateTimeUtilsTest.class);

	@Test
	public void test1() {
		String date2string = DateTimeUtils.date2string(null);
		logger.info(date2string);
	}
	
	@Test
	public void test2() {
		Date string2date = DateTimeUtils.string2date("2016-04-07");
		logger.info(string2date.toString());
	}
	
	@Test
	public void test3() {
		logger.info(""+DateTimeUtils.isAM(new Date()));
	}
	
	@Test
	public void test4() {
		Date start = DateTimeUtils.string2date("2016-03-07");
		Date end = DateTimeUtils.string2date("2016-04-07");
		logger.info(""+DateTimeUtils.getIntervalOfDay(end, start));
	}

}
