package com.ksn.core.ice;

import com.ksn.worker.ice.printer._PrinterIceDisp;

import Ice.Current;

public class PrinterWorker extends _PrinterIceDisp{

	private static final long serialVersionUID = 8195935972846857470L;

	@Override
	public void printString(String s, Current __current) {
		System.out.println(s);
	}

}
