package com.ksn.core.nanomsg;

import nanomsg.reqrep.ReqSocket;

public class ReqClient {

	public static void main(String[] args) {
		System.load("D:\\cli\\nanomsg\\bin\\nanomsg.dll");
		ReqSocket client = new ReqSocket();
		client.connect("tcp:///tmp/sock");

	}

}
