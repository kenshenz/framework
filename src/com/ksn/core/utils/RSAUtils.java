package com.ksn.core.utils;

import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.Cipher;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RSAUtils {
	
	private static Logger logger = LoggerFactory.getLogger(RSAUtils.class);
	
	private static final String PRIVATE_KEY = "RSAPrivateKey";
	private static final String PUBLIC_KEY = "RSAPublicKey";
	
	/**
	 * 使用私钥解密
	 * @param cipherText 密文（base64编码）
	 * @param key 私钥（base64编码）
	 * @return 明文内容
	 */
	public static String decryptByPrivateKey(String cipherText, String key) {
		byte[] keyBytes = Base64.getDecoder().decode(key);
		PKCS8EncodedKeySpec pck8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
		try {
			KeyFactory keyFactory = KeyFactory.getInstance("RSA");
			PrivateKey privateKey = keyFactory.generatePrivate(pck8KeySpec);
			Cipher cipher = Cipher.getInstance("RSA");
			cipher.init(Cipher.DECRYPT_MODE, privateKey);
			byte[] result = cipher.doFinal(Base64.getDecoder().decode(cipherText));
			return new String(result, "utf-8");
		} catch (Exception e) {
			logger.error("", e);
			return "";
		}
	}
	
	/**
	 * 使用私钥加密
	 * @param plainText 明文
	 * @param key 私钥（base64编码）
	 * @return 密文（base64编码）
	 */
	public static String encryptByPrivateKey(String plainText, String key) {
		byte[] keyBytes = Base64.getDecoder().decode(key);
		PKCS8EncodedKeySpec pck8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
		try {
			KeyFactory keyFactory = KeyFactory.getInstance("RSA");
			PrivateKey privateKey = keyFactory.generatePrivate(pck8KeySpec);
			Cipher cipher = Cipher.getInstance("RSA");
			cipher.init(Cipher.ENCRYPT_MODE, privateKey);
			byte[] result = cipher.doFinal(plainText.getBytes("utf-8"));
			return Base64.getEncoder().encodeToString(result);
		} catch (Exception e) {
			logger.error("", e);
			return "";
		}
	}
	
	/**
	 * 使用公钥解密
	 * @param cipherText 密文（base64编码）
	 * @param key 公钥（base64编码）
	 * @return 明文内容
	 */
	public static String decryptByPublicKey(String cipherText, String key) {
		byte[] keyBytes = Base64.getDecoder().decode(key);
		//公钥要用X509EncodeKeySpec或者RSAPublicKeySpec
		X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(keyBytes);
		try {
			KeyFactory keyFactory = KeyFactory.getInstance("RSA");
			PublicKey publicKey = keyFactory.generatePublic(x509EncodedKeySpec);
			Cipher cipher = Cipher.getInstance("RSA");
			cipher.init(Cipher.DECRYPT_MODE, publicKey);
			byte[] result = cipher.doFinal(Base64.getDecoder().decode(cipherText));
			return new String(result, "utf-8");
		} catch (Exception e) {
			logger.error("", e);
			return "";
		}
	}
	
	/**
	 * 使用公钥加密
	 * @param plainText 明文内容
	 * @param key 公钥（base64编码）
	 * @return 密文（base64编码）
	 */
	public static String encryptByPublicKey(String plainText, String key) {
		byte[] keyBytes = Base64.getDecoder().decode(key);
		//公钥要用X509EncodeKeySpec或者RSAPublicKeySpec
		X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(keyBytes);
		try {
			KeyFactory keyFactory = KeyFactory.getInstance("RSA");
			PublicKey publicKey = keyFactory.generatePublic(x509EncodedKeySpec);
			Cipher cipher = Cipher.getInstance("RSA");
			cipher.init(Cipher.ENCRYPT_MODE, publicKey);
			byte[] result = cipher.doFinal(plainText.getBytes("utf-8"));
			return Base64.getEncoder().encodeToString(result);
		} catch (Exception e) {
			logger.error("", e);
			return "";
		}
	}
	
	/**
	 * 初始化RSA公钥/私钥对
	 * @return
	 * @throws NoSuchAlgorithmException
	 */
	public static Map<String, Key> initKeys() throws NoSuchAlgorithmException {
		return initKeys(1024);
	}
	
	/**
	 * 初始化RSA公钥/私钥对
	 * @param keySize
	 * @return
	 * @throws NoSuchAlgorithmException
	 */
	public static Map<String, Key> initKeys(int keySize) throws NoSuchAlgorithmException {
		KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
		keyPairGenerator.initialize(keySize);
		KeyPair keyPair = keyPairGenerator.genKeyPair();
		
		PublicKey publicKey = keyPair.getPublic();
		PrivateKey privateKey = keyPair.getPrivate();
		
		Map<String, Key> keyMap = new HashMap<String, Key>();
		keyMap.put(PRIVATE_KEY, privateKey);
		keyMap.put(PUBLIC_KEY, publicKey);
		
		return keyMap;
	}
	
	/**
	 * 公钥（base64编码）
	 * @param keyMap
	 * @return
	 */
	public static String getPublicKey(Map<String, Key> keyMap) {
		Key key = keyMap.get(PUBLIC_KEY);
		return Base64.getEncoder().encodeToString(key.getEncoded());
	}
	
	/**
	 * 私钥（base64编码）
	 * @param keyMap
	 * @return
	 */
	public static String getPrivateKey(Map<String, Key> keyMap) {
		Key key = keyMap.get(PRIVATE_KEY);
		return Base64.getEncoder().encodeToString(key.getEncoded());
	}
	
}
