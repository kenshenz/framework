package com.ksn.core.protobuf;

import java.io.IOException;

import org.junit.Test;

import com.ksn.core.protobuf.AddressBookProto.AddressBook;
import com.ksn.core.protobuf.AddressBookProto.Person;
import com.ksn.core.protobuf.AddressBookServiceGrpc.AddressBookServiceBlockingStub;
import com.ksn.core.protobuf.AddressBookServiceGrpc.AddressBookServiceStub;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;

public class AddressBookTest {

	@Test
	public void test() throws IOException {
		//server端
		Server server = ServerBuilder.forPort(9999).addService(AddressBookServiceGrpc.bindService(new AddressBookServer())).build();
		server.start();
		System.out.println("Server started, listening on " + 9999);
		
		//client端
		ManagedChannel channel = ManagedChannelBuilder.forAddress("127.0.0.1", 9999).usePlaintext(true).build();
		AddressBookServiceBlockingStub blockStub = AddressBookServiceGrpc.newBlockingStub(channel);
		AddressBookServiceStub asyncStub = AddressBookServiceGrpc.newStub(channel);
		
		AddressBook addressBook = AddressBook.newBuilder()
				.addPerson(0, Person.newBuilder().setEmail("261883813@qq.com").setId(100).setName("ccf"))
				.addPerson(1, Person.newBuilder().setEmail("kenshenchan@gmail.com").setId(200).setName("kenshen"))
				.build();
		
		AddressBook result = blockStub.showAddressBook(addressBook);
		System.out.println("result!");
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
