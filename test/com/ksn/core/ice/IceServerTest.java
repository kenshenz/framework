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
		//初始化通信者对象
		Communicator ic = null;
		ic = Util.initialize();
		
		//创建适配器，id为SimplePrinter，指定tcp协议，10000端口
		ObjectAdapter adapter = ic.createObjectAdapterWithEndpoints("IceServer", "default -p 10000");
		
		//实例化服务
		PrinterWorker worker = new PrinterWorker();
		logger.info("ice obj id : " + worker.ice_id());
		
		//添加服务
		adapter.add(worker, ic.stringToIdentity("PrinterWorker"));
		
		//启动服务
		adapter.activate();
		logger.info("启动服务");
		
		//启动通信者
		ic.waitForShutdown();
		
		//销毁通信者
		if (ic != null) {
			ic.destroy();
		}
		
	}

}
