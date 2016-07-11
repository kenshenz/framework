package com.ksn.core.protobuf;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Executors;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ksn.core.disruptor.LongEvent;
import com.ksn.core.disruptor.LongEventFactory;
import com.ksn.core.disruptor.LongEventProducer;
import com.ksn.core.disruptor.LongEventWorkHandler;
import com.ksn.core.protobuf.AddressBookProto.AddressBook;
import com.lmax.disruptor.BlockingWaitStrategy;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;

public class GprcWithDisruptorTest {
	
	private static final Logger logger = LoggerFactory.getLogger(GrpcServerTest.class);
	
	LongEventFactory factory;
	
	Disruptor<LongEvent> disruptor;
	RingBuffer<LongEvent> ringBuffer;
	LongEventProducer producer;
	
	public GprcWithDisruptorTest() {
		factory = new LongEventFactory();
		disruptor = new Disruptor<LongEvent>(factory, 1024, Executors.defaultThreadFactory(),
			ProducerType.MULTI, new BlockingWaitStrategy());
		/*disruptor.handleEventsWithWorkerPool(new LongEventWorkHandler(),
				new LongEventWorkHandler(), new LongEventWorkHandler(),
				new LongEventWorkHandler());*/
		LongEventWorkHandler handler = new LongEventWorkHandler();
		List<LongEventWorkHandler> handlerList = new ArrayList<LongEventWorkHandler>();
		for (int i = 0; i < 10; i++) {
			handlerList.add(handler);
		}
		disruptor.handleEventsWithWorkerPool(handlerList.toArray(new LongEventWorkHandler[0]));
		
		ringBuffer = disruptor.start();
		producer = new LongEventProducer(ringBuffer);
	}

	@Test
	public void test() throws InterruptedException, IOException{
		//server端
		Server server = ServerBuilder.forPort(50051)
					.addService(
							AddressBookServiceGrpc
									.bindService(new AddressBookServer()))
					.build().start();
		System.out.println("Server started, listening on " + 50051);
		
		Runtime.getRuntime().addShutdownHook(new Thread() {  
			@Override  
			public void run() {  
				// Use stderr here since the logger may have been reset by its JVM shutdown hook.  
				System.err.println("*** shutting down gRPC server since JVM is shutting down");  
//				HelloWorldServer.this.stop();  
				System.err.println("*** server shut down");  
			}
		});
		
		if (server != null) {
			server.awaitTermination();
		}
	}
	
	public class AddressBookServer implements AddressBookServiceGrpc.AddressBookService {

		@Override
		public void showAddressBook(AddressBook request, StreamObserver<AddressBook> responseObserver) {
			logger.info("showAddressBook start");
			ByteBuffer bb = ByteBuffer.allocate(8);
			
			for (long l = 0; l < 1; l++) {
				bb.putLong(0, l);
				producer.publish(bb);
			}
			logger.info("showAddressBook end");
			responseObserver.onNext(request);
			responseObserver.onCompleted();
		}
		
	}

}
