package com.ksn.core.utils;

import java.io.UnsupportedEncodingException;
import java.util.Base64;

import org.junit.Test;

public class Base64Test {

	@Test
	public void test() throws UnsupportedEncodingException {
		String result = new String(Base64.getDecoder().decode("eyJwb3N0Q3JlZGl0VG9rZW5JZCI6ImV3b2dJQ0owYjJ0bGJrbGtJaUE2SUNKd2NHMXZibVY1WldVNU1ESTVNRFUyTm1FMU1tUXhZbVkzT0dOa01EQXlOMk5pTVRZNU1UUWlMQW9nSUNKdmN5SWdPaUFpYVU5VElpd0tJQ0FpY0hKdlptbHNaVlJwYldVaUlEb2dNakE1TlN3S0lDQWlkbVZ5YzJsdmJpSWdPaUFpTWk0eExqUWlDbjA9IiwicG9zdENyZWRpdEFkZHIiOiLlub/kuJznnIHlub/lt57luILlpKnmsrPljLrlpKnmsrPlpKfljqYiLCJhY2NvdW50SWQiOiIwMTU0ZTg3OGExOTBZSUk4QUhCaU5sNDBlMkdPMzFRayIsInBvc3RDcmVkaXRDb21wYW55Ijoi5Y2X5rW35riU5p2RIiwiYml6SWQiOiIwMTU0ZWMyMjNmZmM0SUxjUmdLSTNNQ1VUVDhXMzRHWCIsInBvc3RDcmVkaXREYXRlIjoiMjAxNi0wNS0yNiAxNjoxNTo0OCJ9"), "utf-8");
		System.out.println(result);
	}
	
	@Test
	public void test2() throws UnsupportedEncodingException {
		String str = "{\"accountId\": \"0154dd1ba1d8S1Gffa2IKFdCSmhR4eY3\",\"phoneNum\": \"18676656114\",\"regDate\": \"2016-05-23 18:14:16\",\"bizKindName\": \"VIRTUALCARD\",\"regSrcName\": \"ios\",\"userName\": \"孙麟\",\"idCardNum\": \"230402198607220119\",\"authResult\": 1,\"authDate\": \"2016-05-23 18:14:16\",\"idCardFrontPhotoUrl\": \"http://zhongchou.b0.upaiyun.com/PPLoan_iOS/z0154dd1ba1d8S1Gffa2IKFdCSmhR4eY31463998502962\",\"idCardBackPhotoUrl\": \"http://zhongchou.b0.upaiyun.com/PPLoan_iOS/z0154dd1ba1d8S1Gffa2IKFdCSmhR4eY31463998601083\",\"idCardHandPhotoUrl\": \"http://zhongchou.b0.upaiyun.com/PPLoan_iOS/z0154dd1ba1d8S1Gffa2IKFdCSmhR4eY31463998743133\"}";
		String result = Base64.getEncoder().encodeToString(str.getBytes("utf-8"));
		System.out.println(result);
	}

}
