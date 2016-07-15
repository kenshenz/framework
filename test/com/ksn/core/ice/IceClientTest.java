package com.ksn.core.ice;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ksn.worker.ice.printer.PrinterIcePrx;
import com.ksn.worker.ice.printer.PrinterIcePrxHelper;

import Ice.Communicator;
import Ice.ObjectPrx;
import Ice.Util;

public class IceClientTest {
	
	Logger logger = LoggerFactory.getLogger(getClass());

	@Test
	public void test() {
		Communicator ic = Util.initialize();
		ObjectPrx proxy = ic.stringToProxy("SimplePrinter:default -p 10000");
		
		PrinterIcePrx workerProxy = PrinterIcePrxHelper.checkedCast(proxy);
		
		if (workerProxy == null) {
			throw new Error("invaild proxy");
		}
		
		workerProxy.printString("hello zeroc ice");
		
		if (ic != null) {
			ic.destroy();
		}
	}

}
