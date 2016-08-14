package com.ksn.core.pojo.resp;

import java.io.Serializable;

public class Response implements Serializable {

	private static final long serialVersionUID = -6837089811930770154L;

	private String code;
	private String msg;

	public Response() {
	}

	public Response(String code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	public static Response success() {
		return new Response("0", "success");
	}
	
	public static Response failure() {
		return new Response("-1", "failure");
	}
	
	public static Response error() {
		return new Response("-2", "error");
	}

}
