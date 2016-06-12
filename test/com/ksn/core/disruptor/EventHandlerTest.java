package com.ksn.core.disruptor;

import java.nio.ByteBuffer;
import java.util.concurrent.Executors;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.lmax.disruptor.EventHandler;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;

public class EventHandlerTest {
	
	LongEventFactory factory;
	
	@Before
	public void before() {
		factory = new LongEventFactory();
	}

	@Test
	@Ignore
	public void test() throws InterruptedException {
		Disruptor<LongEvent> disruptor = new Disruptor<LongEvent>(factory, 1024, Executors.defaultThreadFactory());
		
		disruptor.handleEventsWith(new LongEventHandler(), new LongEventHandler(), new LongEventHandler());
		
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
	
	/**
	 * 生产者必须等到所有消费者处理完才能往ringbuffer里面填
	 */
	@Test
	public void test2() {
		Disruptor<LongEvent> disruptor = new Disruptor<LongEvent>(factory, 8, Executors.defaultThreadFactory());
		
		disruptor.handleEventsWith(new EventHandler<LongEvent>() {

			@Override
			public void onEvent(LongEvent event, long sequence, boolean endOfBatch) throws Exception {
				System.out.println("event: 1, sequence: " + sequence);
			}
		}, new EventHandler<LongEvent>() {

			@Override
			public void onEvent(LongEvent event, long sequence, boolean endOfBatch) throws Exception {
				System.out.println("event: 2, sequence: " + sequence);
				Thread.currentThread().sleep(500000);
			}
		});
		
		disruptor.start();
		
		RingBuffer<LongEvent> ringBuffer = disruptor.getRingBuffer();
		
		LongEventProducer producer = new LongEventProducer(ringBuffer);
		
		ByteBuffer bb = ByteBuffer.allocate(8);
		
		for (long l = 0; l < 20; l++) {
			bb.putLong(0, l);
			producer.publish(bb);
		}
	}

}
