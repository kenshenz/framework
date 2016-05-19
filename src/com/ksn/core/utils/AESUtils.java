package com.ksn.core.utils;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

public class AESUtils {

	/**
	 * AES加密，并输出base64字符串
	 * 
	 * @param plainText 明文内容
	 * @param key 密钥
	 * @return 经过base64处理的密文
	 * @throws UnsupportedEncodingException
	 * @throws NoSuchAlgorithmException
	 * @throws NoSuchPaddingException
	 * @throws InvalidKeyException
	 * @throws IllegalBlockSizeException
	 * @throws BadPaddingException
	 */
	public static String encrypt(String plainText, String key)
			throws UnsupportedEncodingException, NoSuchAlgorithmException,
			NoSuchPaddingException, InvalidKeyException,
			IllegalBlockSizeException, BadPaddingException {
		byte[] keyBytes = key.getBytes("utf-8");
		SecretKeySpec secretKeySpec = new SecretKeySpec(keyBytes, "AES");
		Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
		cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
		byte[] result = cipher.doFinal(plainText.getBytes("utf-8"));
		return Base64.getEncoder().encodeToString(result);
	}

	/**
	 * AES解密
	 * @param cipherText 经过base64处理的密文内容
	 * @param key 密钥
	 * @return 明文
	 * @throws NoSuchAlgorithmException
	 * @throws NoSuchPaddingException
	 * @throws UnsupportedEncodingException
	 * @throws InvalidKeyException
	 * @throws IllegalBlockSizeException
	 * @throws BadPaddingException
	 */
	public static String decrypt(String cipherText, String key)
			throws NoSuchAlgorithmException, NoSuchPaddingException,
			UnsupportedEncodingException, InvalidKeyException,
			IllegalBlockSizeException, BadPaddingException {
		byte[] keyBytes = key.getBytes("utf-8");
		SecretKeySpec secretKeySpec = new SecretKeySpec(keyBytes, "AES");
		Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
		cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);
		byte[] result = cipher.doFinal(Base64.getDecoder().decode(cipherText));
		return new String(result, "utf-8");
	}

}
