package com.ksn.core.disruptor;

import java.nio.ByteBuffer;
import java.util.concurrent.Executors;

import org.junit.Test;

import com.lmax.disruptor.BlockingWaitStrategy;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;

public class WorkHandlerTest {

	@Test
	public void test() {
		LongEventFactory factory = new LongEventFactory();
		
		Disruptor<LongEvent> disruptor = new Disruptor<LongEvent>(factory, 1024, Executors.defaultThreadFactory(),
				ProducerType.MULTI, new BlockingWaitStrategy());
		
		disruptor.handleEventsWithWorkerPool(new LongEventWorkHandler(), new LongEventWorkHandler());
		
		RingBuffer<LongEvent> ringBuffer = disruptor.start();
		
		LongEventProducer producer = new LongEventProducer(ringBuffer);
		
		ByteBuffer bb = ByteBuffer.allocate(8);
		
		for (long l = 0; true; l++) {
			bb.putLong(0, l);
			producer.publish(bb);
//			Thread.sleep(1);
		}
		
		
	}

}
