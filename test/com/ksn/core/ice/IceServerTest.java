package com.ksn.core.ice;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import Ice.Communicator;
import Ice.ObjectAdapter;
import Ice.Util;

public class IceServerTest {
	
	Logger logger = LoggerFactory.getLogger(getClass());

	@Test
	public void test() {
		Communicator ic = null;

		ic = Util.initialize();
		ObjectAdapter adapter = ic.createObjectAdapterWithEndpoints("SimplePrinter", "default -p 10000");
		
		PrinterWorker worker = new PrinterWorker();
		
		//添加服务
		adapter.add(worker, ic.stringToIdentity("SimplePrinter"));
		
		//启动服务
		adapter.activate();
		logger.info("启动服务");
		
		//启动通信
		ic.waitForShutdown();
		
		if (ic != null) {
			ic.destroy();
		}
		
	}

}
