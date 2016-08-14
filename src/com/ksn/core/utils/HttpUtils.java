package com.ksn.core.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.TimeUnit;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;

public class HttpUtils {
	public static HttpClient httpClient = HttpClientBuilder.create().setConnectionTimeToLive(30, TimeUnit.MICROSECONDS)
			.build();
	
	public static String doGet(String uri) {
		HttpGet get = new HttpGet(uri);
		HttpResponse resp;
		try {
			resp = httpClient.execute(get);
			HttpEntity entity = resp.getEntity();
			BufferedReader is = new BufferedReader(new InputStreamReader(entity.getContent()));
			StringBuilder sb = new StringBuilder();
			String line = null;
			while ((line = is.readLine()) != null) {
				sb.append(line);
			}
			String result = sb.toString();
			return result;
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			get.releaseConnection();
		}
		
		return "";
	}

}
