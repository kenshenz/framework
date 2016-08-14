package com.ksn.core.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MD5Utils {
	
	public static final Logger logger = LoggerFactory.getLogger(MD5Utils.class);
	
	public static final String MD5_SALT = "kenshenz";
	
	/**
	 * 使用md5算法加密字符串
	 * @param inStr
	 * @return
	 */
	public static String string2md5(String inStr) {
		String inStrWithSalt = inStr + MD5_SALT;
		MessageDigest md5 = null;
		try {
			md5 = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			logger.error("md5加密异常: ", e);
			return "";
		}
		md5.update(inStrWithSalt.getBytes());
		byte[] md5Bytes = md5.digest();
		
		StringBuffer hexVal = new StringBuffer();
		for (int i = 0, len = md5Bytes.length; i < len; i++) {
			int val = md5Bytes[i] & 0xff;
			if (val < 16) {
				hexVal.append("0");
			}
			hexVal.append(Integer.toHexString(val));
		}
		
		return hexVal.toString();
		
	}

}
