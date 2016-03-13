package com.ksn.core.utils;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MD5UtilsTest {
	
	public static final Logger logger = LoggerFactory.getLogger(MD5UtilsTest.class);

	@Test
	public void test() {
		logger.debug("md5:" + MD5Utils.string2md5("hello world"));
		logger.debug("md5:" + MD5Utils.string2md5("HELLO WORLD"));
		logger.debug("md5:" + MD5Utils.string2md5("helloworld"));
	}

}
