package com.ksn.core.protobuf;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.junit.Test;

import com.ksn.core.protobuf.AddressBookProto.AddressBook;
import com.ksn.core.protobuf.AddressBookProto.Person;
import com.ksn.core.protobuf.AddressBookServiceGrpc.AddressBookServiceBlockingStub;
import com.ksn.core.protobuf.AddressBookServiceGrpc.AddressBookServiceStub;

public class AddressBookTest {

	@Test
	public void test() throws IOException, InterruptedException {
		//client端
		ManagedChannel channel = ManagedChannelBuilder.forAddress("127.0.0.1", 50051).usePlaintext(true).build();
		AddressBookServiceBlockingStub blockStub = AddressBookServiceGrpc.newBlockingStub(channel);
		AddressBookServiceStub asyncStub = AddressBookServiceGrpc.newStub(channel);
		
		AddressBook addressBook = AddressBook.newBuilder()
			.addPerson(0, Person.newBuilder().setEmail("261883813@qq.com").setId(100).setName("ccf"))
			.addPerson(1, Person.newBuilder().setEmail("kenshenchan@gmail.com").setId(200).setName("kenshen"))
			.build();
		
		AddressBook result = blockStub.showAddressBook(addressBook);
		for (Person p : result.getPersonList()) {
			System.out.println("person: email=" + p.getEmail() + "; name=" + p.getName());
		}
		System.out.println("result!");
		
		channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
	}
	
	
}
