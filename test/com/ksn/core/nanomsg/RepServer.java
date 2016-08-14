package com.ksn.core.nanomsg;

import nanomsg.reqrep.RepSocket;

public class RepServer {

	public static void main(String[] args) {
		final RepSocket server = new RepSocket();
		server.bind("tcp:///tmp/sock");
	}
}
