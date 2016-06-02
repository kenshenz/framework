package com.ksn.core.disruptor;

import com.lmax.disruptor.WorkHandler;

public class LongEventWorkHandler implements WorkHandler<LongEvent>{

	@Override
	public void onEvent(LongEvent event) throws Exception {
		System.out.println(Thread.currentThread().getName() + " sequence: " + event.get());
		Thread.currentThread().sleep(3);
	}

}
