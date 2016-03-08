package com.ksn.framework.utils;

import org.joda.time.DateTime;
import org.joda.time.DateTime.Property;
import org.junit.Test;

public class DateTimeUtilsTest {

	@Test
	public void test1() {
		DateTime dt = new DateTime();
		
		Property dayOfYear = dt.dayOfYear();
		System.out.println(dayOfYear.getAsText());
		
		DateTime withDate = dt.withDate(2001, 12, 12);
		
		System.out.println(withDate);
		
	}

}
