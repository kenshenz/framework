package com.ksn.framework.utils;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.AfterClass;
import org.junit.Test;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class JsonUtilsTest {
	
	private Gson gson = new Gson();
	private GsonBuilder gsonBuilder = new GsonBuilder();

	@Test
	public void test1_gson() {
		Map fromJson = gson.fromJson("{\"name\":1}", Map.class);
		
		int i = 1;
		
	}
	
	@Test
	public void test2_gsonBuilder() {
		Gson gson = gsonBuilder.setDateFormat("yyyy-MM-dd HH:mm:ssss")
		.setPrettyPrinting().create();
		
		Map obj = new HashMap();
		obj.put("name", "tom");
		obj.put("age", 18);
		String json = gson.toJson(obj);
		
		int i = 1;
	}

}
