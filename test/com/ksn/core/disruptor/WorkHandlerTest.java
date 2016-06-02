package com.ksn.core.disruptor;

import static org.junit.Assert.fail;

import java.nio.ByteBuffer;
import java.util.concurrent.Executors;

import org.junit.Test;

import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;

public class WorkHandlerTest {

	@Test
	public void test() {
		LongEventFactory factory = new LongEventFactory();
		
		Disruptor<LongEvent> disruptor = new Disruptor<LongEvent>(factory, 1024, Executors.defaultThreadFactory());
		
		disruptor.handleEventsWithWorkerPool(new LongEventWorkHandler(), new LongEventWorkHandler());
		
		disruptor.start();
		
		
		RingBuffer<LongEvent> ringBuffer = disruptor.getRingBuffer();
		
		LongEventProducer producer = new LongEventProducer(ringBuffer);
		
		ByteBuffer bb = ByteBuffer.allocate(8);
		
		for (long l = 0; l < 20; l++) {
			bb.putLong(0, l);
			producer.publish(bb);
//			Thread.sleep(1);
		}
		
		
	}

}
