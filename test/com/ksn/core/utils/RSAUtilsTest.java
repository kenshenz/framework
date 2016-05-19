package com.ksn.core.utils;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.util.Map;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import org.junit.Ignore;
import org.junit.Test;

public class RSAUtilsTest {
	
	private static final String PRICATE_KEY = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBALH63+TpjFeMRcEz8OMxNb2WdDZM86WcFNl3Li088OiLeV09942aOAwCf08juQplzidI5MaVv1ewnUVHKPU37b7b0VGmGr+ZF+aILOPeJhstXwwexqrXmtBcoDsnlqbGk5eLd/tFQj3ETkT+1T2DurnwL85/Af/hU1Efim/wIgI/AgMBAAECgYByksrBJSvITC+3cUV4K/ypgIptTlpm2TR+zf829g0r1mWZhcOwL4A3EwB7pMaBEAqHNaC4yju0KqkDqQyP16I+8SOAwo7IbZLHuy2oufOH2oy4o/U/JGzNM9fUx3/L2QL571tAvmJ1h301Ksw6RCpN9anaobkI7ubt0F59m1kmOQJBAOH/Bts8MxXy7Y6yT2g6D7XFAJPA8sPpJM3qNjRrgrEbEhhAvKATM3H0TviGXD0MQ+D3c18dJH4lafBPZUEF0x0CQQDJm+l2Y2Z4YnzGvhL5K3x4RZQMmP/R8Tc+URf9MgBuC6N9iUU5n1Jlm0dBind5Jq2kVB1trLyceskV8ySYVrALAkA3IfQWP9VmoWD60t00XBXBDlJZuVSAHrxvz5dxqlizxBrh98kMdCK0xhkN4BlYe0mD9Foc9gsVCyOREI1vE3CxAkEAjSLHmihV2NrhjOQNNj+6IBxdW0BUfm1GBisRByBU6rTdHAnX21HshdLhXEGKB9cK4io8e8XcOBNA6EvRh4/QJwJBAKa1kcSqQaEMaS9yRA8qMy0fqmT+qm1sMmo3t8dk1cVLNr8fABJNVSFIq5LKVetxfMg48yGsnydWoV3UNhBtNXI=";
	private static final String PUBLIC_KEY = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCx+t/k6YxXjEXBM/DjMTW9lnQ2TPOlnBTZdy4tPPDoi3ldPfeNmjgMAn9PI7kKZc4nSOTGlb9XsJ1FRyj1N+2+29FRphq/mRfmiCzj3iYbLV8MHsaq15rQXKA7J5amxpOXi3f7RUI9xE5E/tU9g7q58C/OfwH/4VNRH4pv8CICPwIDAQAB";

	@Test
	@Ignore
	public void test() throws NoSuchAlgorithmException {
		Map<String, Key> initKeys = RSAUtils.initKeys();
		String publicKey = RSAUtils.getPublicKey(initKeys);
		String privateKey = RSAUtils.getPrivateKey(initKeys);
		
		System.out.println("公钥：" + publicKey);
		System.out.println("密钥：" + privateKey);
	}
	
	@Test
	@Ignore
	public void test_encryptByPrivateKey() throws InvalidKeyException, NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException {
		String plainText = "hello world";
		String result = RSAUtils.encryptByPrivateKey(plainText, PRICATE_KEY);
		System.out.println(result);
	}
	
	@Test
	@Ignore
	public void test_decryptByPublicKey() throws InvalidKeyException, IllegalBlockSizeException, BadPaddingException, NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, UnsupportedEncodingException {
		String cipherText = "ONsruu0naRiVkAl1VPJ5qWPltEGWXrWaqinxea/1lFjZkwcJh5qvBgT4sl6DY4pLj8FlEet6kHSI779PZrmeUts3X7CraFtm3w90tM/SRnFtb1IF/JWZEGdOwAV67HFOY24C56eNrusK1+R7E2lEZkc1e+IUl1Tg31xo21RjJYs=";
		String result = RSAUtils.decryptByPublicKey(cipherText, PUBLIC_KEY);
		System.out.println(result);
	}
	
	@Test
	public void test_encryptByPublicKey() throws InvalidKeyException, NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException {
		String plainText = "hello world";
		String result = RSAUtils.encryptByPublicKey(plainText, PUBLIC_KEY);
		System.out.println(result);
	}
	
	@Test
	public void test_decryptByPrivateKey() throws InvalidKeyException, IllegalBlockSizeException, BadPaddingException, NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, UnsupportedEncodingException {
		String cipherText = "Y4WJUPSglidls+/abkRAmZ8iJsKv1AKVyTCEzR0RwJK9Axv8HUVB4zvy1oXQqCVnNMQJbKR5O8jmGywBI7QoVoLvYIb4u8+eRLMmZsI46xYya12sRMmmVZwn32NfchU3e+Vht0Hl4oZgw36xXQuI3yW/UszP4ckEYzAmP1FPz18=";
		String result = RSAUtils.decryptByPrivateKey(cipherText, PRICATE_KEY);
		System.out.println(result);
	}

}
