package com.ksn.core.utils;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

public class RSAUtils {
	
	private static final String PRIVATE_KEY = "RSAPrivateKey";
	private static final String PUBLIC_KEY = "RSAPublicKey";
	
	/**
	 * 使用私钥解密
	 * @param cipherText 密文（base64编码）
	 * @param key 私钥（base64编码）
	 * @return 明文内容
	 * @throws IllegalBlockSizeException
	 * @throws BadPaddingException
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeyException
	 * @throws InvalidKeySpecException
	 * @throws NoSuchPaddingException
	 * @throws UnsupportedEncodingException 
	 */
	public static String decryptByPrivateKey(String cipherText, String key)
			throws IllegalBlockSizeException, BadPaddingException,
			NoSuchAlgorithmException, InvalidKeyException,
			InvalidKeySpecException, NoSuchPaddingException,
			UnsupportedEncodingException {
		byte[] keyBytes = Base64.getDecoder().decode(key);
		PKCS8EncodedKeySpec pck8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
		KeyFactory keyFactory = KeyFactory.getInstance("RSA");
		PrivateKey privateKey = keyFactory.generatePrivate(pck8KeySpec);
		Cipher cipher = Cipher.getInstance("RSA");
		cipher.init(Cipher.DECRYPT_MODE, privateKey);
		byte[] result = cipher.doFinal(Base64.getDecoder().decode(cipherText));
		return new String(result, "utf-8");
	}
	
	/**
	 * 使用私钥加密
	 * @param plainText 明文
	 * @param key 私钥（base64编码）
	 * @return 密文（base64编码）
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeySpecException
	 * @throws NoSuchPaddingException
	 * @throws InvalidKeyException
	 * @throws IllegalBlockSizeException
	 * @throws BadPaddingException
	 * @throws UnsupportedEncodingException
	 */
	public static String encryptByPrivateKey(String plainText, String key)
			throws NoSuchAlgorithmException, InvalidKeySpecException,
			NoSuchPaddingException, InvalidKeyException,
			IllegalBlockSizeException, BadPaddingException,
			UnsupportedEncodingException {
		byte[] keyBytes = Base64.getDecoder().decode(key);
		PKCS8EncodedKeySpec pck8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
		KeyFactory keyFactory = KeyFactory.getInstance("RSA");
		PrivateKey privateKey = keyFactory.generatePrivate(pck8KeySpec);
		Cipher cipher = Cipher.getInstance("RSA");
		cipher.init(Cipher.ENCRYPT_MODE, privateKey);
		byte[] result = cipher.doFinal(plainText.getBytes("utf-8"));
		return Base64.getEncoder().encodeToString(result);
	}
	
	/**
	 * 使用公钥解密
	 * @param cipherText 经过base64处理的密文内容
	 * @param key 经过base64处理的公钥
	 * @return 明文内容
	 * @throws IllegalBlockSizeException
	 * @throws BadPaddingException
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeyException
	 * @throws InvalidKeySpecException
	 * @throws NoSuchPaddingException
	 * @throws UnsupportedEncodingException
	 */
	public static String decryptByPublicKey(String cipherText, String key)
			throws IllegalBlockSizeException, BadPaddingException,
			NoSuchAlgorithmException, InvalidKeyException,
			InvalidKeySpecException, NoSuchPaddingException,
			UnsupportedEncodingException {
		byte[] keyBytes = Base64.getDecoder().decode(key);
		//公钥要用X509EncodeKeySpec或者RSAPublicKeySpec
		X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(keyBytes);
		KeyFactory keyFactory = KeyFactory.getInstance("RSA");
		PublicKey publicKey = keyFactory.generatePublic(x509EncodedKeySpec);
		Cipher cipher = Cipher.getInstance("RSA");
		cipher.init(Cipher.DECRYPT_MODE, publicKey);
		byte[] result = cipher.doFinal(Base64.getDecoder().decode(cipherText));
		return new String(result, "utf-8");
	}
	
	/**
	 * 使用公钥加密
	 * @param plainText 明文内容
	 * @param key 经过base64处理的公钥
	 * @return 经过base64处理的密文内容
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeySpecException
	 * @throws NoSuchPaddingException
	 * @throws InvalidKeyException
	 * @throws IllegalBlockSizeException
	 * @throws BadPaddingException
	 * @throws UnsupportedEncodingException
	 */
	public static String encryptByPublicKey(String plainText, String key)
			throws NoSuchAlgorithmException, InvalidKeySpecException,
			NoSuchPaddingException, InvalidKeyException,
			IllegalBlockSizeException, BadPaddingException,
			UnsupportedEncodingException {
		byte[] keyBytes = Base64.getDecoder().decode(key);
		//公钥要用X509EncodeKeySpec或者RSAPublicKeySpec
		X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(keyBytes);
		KeyFactory keyFactory = KeyFactory.getInstance("RSA");
		PublicKey publicKey = keyFactory.generatePublic(x509EncodedKeySpec);
		Cipher cipher = Cipher.getInstance("RSA");
		cipher.init(Cipher.ENCRYPT_MODE, publicKey);
		byte[] result = cipher.doFinal(plainText.getBytes("utf-8"));
		return Base64.getEncoder().encodeToString(result);
	}
	
	/**
	 * 初始化RSA公钥/私钥对
	 * @return
	 * @throws NoSuchAlgorithmException
	 */
	public static Map<String, Key> initKeys() throws NoSuchAlgorithmException {
		KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
		keyPairGenerator.initialize(1024);
		KeyPair keyPair = keyPairGenerator.genKeyPair();
		
		PublicKey publicKey = keyPair.getPublic();
		PrivateKey privateKey = keyPair.getPrivate();
		
		Map<String, Key> keyMap = new HashMap<String, Key>();
		keyMap.put(PRIVATE_KEY, privateKey);
		keyMap.put(PUBLIC_KEY, publicKey);
		
		return keyMap;
	}
	
	/**
	 * 返回经过base64处理的公钥
	 * @param keyMap
	 * @return
	 */
	public static String getPublicKey(Map<String, Key> keyMap) {
		Key key = keyMap.get(PUBLIC_KEY);
		return Base64.getEncoder().encodeToString(key.getEncoded());
	}
	
	/**
	 * 返回经过base64处理的私钥
	 * @param keyMap
	 * @return
	 */
	public static String getPrivateKey(Map<String, Key> keyMap) {
		Key key = keyMap.get(PRIVATE_KEY);
		return Base64.getEncoder().encodeToString(key.getEncoded());
	}
	
}
