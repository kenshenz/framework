package com.ksn.core.protobuf;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;

import java.io.IOException;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ksn.core.protobuf.AddressBookProto.AddressBook;
import com.ksn.core.protobuf.AddressBookProto.Person;

public class GrpcServerTest {
	private static final Logger logger = LoggerFactory.getLogger(GrpcServerTest.class);

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
			System.out.println("AddressBook Server");
			/*for (Person p : request.getPersonList()) {
				System.out.println("person: email=" + p.getEmail() + "; name=" + p.getName());
			}*/
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			responseObserver.onNext(request);
			responseObserver.onCompleted();
		}
		
	}

}
