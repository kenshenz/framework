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

/**
 * Server that manages startup/shutdown of a {@code Greeter} server.
 */
public class HelloWorldServer {
  private static final Logger logger = LoggerFactory.getLogger(HelloWorldServer.class);

  /* The port on which the server should run */
  private int port = 50051;
  private Server server;

  private void start() {
    try {
		server = ServerBuilder.forPort(port)
		    .addService(AddressBookServiceGrpc
					.bindService(new AddressBookServer()))
		    .build()
		    .start();
	} catch (IOException e) {
		logger.error("服务异常：", e);
	}
    logger.info("Server started, listening on " + port);
    Runtime.getRuntime().addShutdownHook(new Thread() {
      @Override
      public void run() {
        // Use stderr here since the logger may have been reset by its JVM shutdown hook.
        System.err.println("*** shutting down gRPC server since JVM is shutting down");
        HelloWorldServer.this.stop();
        System.err.println("*** server shut down");
      }
    });
  }

  private void stop() {
    if (server != null) {
      server.shutdown();
    }
  }

  /**
   * Await termination on the main thread since the grpc library uses daemon threads.
   */
  private void blockUntilShutdown() throws InterruptedException {
    if (server != null) {
      server.awaitTermination();
    }
  }

  /**
   * Main launches the server from the command line.
 * @throws IOException 
 * @throws InterruptedException 
   */
  /*public static void main(String[] args) throws IOException, InterruptedException {
    final HelloWorldServer server = new HelloWorldServer();
    server.start();
    server.blockUntilShutdown();
  }*/
  
	@Test
	public void test() throws IOException, InterruptedException {
		final HelloWorldServer server = new HelloWorldServer();
	    server.start();
	    server.blockUntilShutdown();
	}

  public class AddressBookServer implements AddressBookServiceGrpc.AddressBookService {

		@Override
		public void showAddressBook(AddressBook request, StreamObserver<AddressBook> responseObserver) {
			System.out.println("AddressBook Server");
			for (Person p : request.getPersonList()) {
				System.out.println("person: email=" + p.getEmail() + "; name=" + p.getName());
			}
			responseObserver.onNext(request);
			responseObserver.onCompleted();
		}
		
	}
}