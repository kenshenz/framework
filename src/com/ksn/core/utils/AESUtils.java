package com.ksn.core.utils;

import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AESUtils {
	
	private static Logger logger = LoggerFactory.getLogger(AESUtils.class);
	
	private static final String TRANSFORMATION = "AES/ECB/PKCS5Padding";

	/**
	 * AES加密，并输出base64字符串
	 * 
	 * @param plainText 明文内容
	 * @param key 密钥
	 * @return 经过base64处理的密文
	 */
	public static String encrypt(String plainText, String key) {
		try {
			byte[] keyBytes = key.getBytes("utf-8");
			SecretKeySpec secretKeySpec = new SecretKeySpec(keyBytes, "AES");
			Cipher cipher = Cipher.getInstance(TRANSFORMATION);
			cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
			byte[] result = cipher.doFinal(plainText.getBytes("utf-8"));
			return Base64.getEncoder().encodeToString(result);
		} catch (Exception e) {
			logger.error("", e);
			return "";
		}
	}

	/**
	 * AES解密
	 * @param cipherText 经过base64处理的密文内容
	 * @param key 密钥
	 * @return 明文
	 */
	public static String decrypt(String cipherText, String key) {
		try {
			byte[] keyBytes = key.getBytes("utf-8");
			SecretKeySpec secretKeySpec = new SecretKeySpec(keyBytes, "AES");
			Cipher cipher = Cipher.getInstance(TRANSFORMATION);
			cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);
			byte[] result = cipher.doFinal(Base64.getDecoder().decode(cipherText));
			return new String(result, "utf-8");
		} catch (Exception e) {
			logger.error("", e);
			return "";
		}
	}

}
