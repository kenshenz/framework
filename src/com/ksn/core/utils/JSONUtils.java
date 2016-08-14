package com.ksn.core.utils;

import com.google.gson.Gson;

public class JSONUtils {
	
	private static final Gson gson;
	
	static {
		gson = new Gson();
	}
	
	public static Gson getGson() {
		return gson;
	}

}
