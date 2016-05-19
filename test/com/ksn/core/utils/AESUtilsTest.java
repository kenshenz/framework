package com.ksn.core.utils;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import org.junit.Test;

public class AESUtilsTest {

	@Test
	public void test() throws InvalidKeyException, UnsupportedEncodingException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException {
		String key = "1234567890123456";
		String s1 = "hello 我平日里@#！#！￥salkjf下次V型从v）（&*&（……&*……%￥……";
		long start = System.currentTimeMillis();
		
		/*for (int i = 0; i < 100000; i++) {
			System.out.println(AESUtils.encrypt(s1, key));
		}*/
		
		String result = AESUtils.encrypt(s1, key);
		System.out.println("加密后：" + result);
		System.out.println("解密后：" + AESUtils.decrypt(result, key));
		
		System.out.println(System.currentTimeMillis() - start);
	}

}
