package com.ksn.core.protobuf;

import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.asyncServerStreamingCall;
import static io.grpc.stub.ClientCalls.asyncClientStreamingCall;
import static io.grpc.stub.ClientCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.blockingServerStreamingCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncServerStreamingCall;
import static io.grpc.stub.ServerCalls.asyncClientStreamingCall;
import static io.grpc.stub.ServerCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 0.14.0)",
    comments = "Source: AddressBook.proto")
public class AddressBookServiceGrpc {

  private AddressBookServiceGrpc() {}

  public static final String SERVICE_NAME = "framework.AddressBookService";

  // Static method descriptors that strictly reflect the proto.
  @io.grpc.ExperimentalApi
  public static final io.grpc.MethodDescriptor<com.ksn.core.protobuf.AddressBookProto.AddressBook,
      com.ksn.core.protobuf.AddressBookProto.AddressBook> METHOD_SHOW_ADDRESS_BOOK =
      io.grpc.MethodDescriptor.create(
          io.grpc.MethodDescriptor.MethodType.UNARY,
          generateFullMethodName(
              "framework.AddressBookService", "ShowAddressBook"),
          io.grpc.protobuf.ProtoUtils.marshaller(com.ksn.core.protobuf.AddressBookProto.AddressBook.getDefaultInstance()),
          io.grpc.protobuf.ProtoUtils.marshaller(com.ksn.core.protobuf.AddressBookProto.AddressBook.getDefaultInstance()));

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static AddressBookServiceStub newStub(io.grpc.Channel channel) {
    return new AddressBookServiceStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static AddressBookServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new AddressBookServiceBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary and streaming output calls on the service
   */
  public static AddressBookServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new AddressBookServiceFutureStub(channel);
  }

  /**
   */
  public static interface AddressBookService {

    /**
     */
    public void showAddressBook(com.ksn.core.protobuf.AddressBookProto.AddressBook request,
        io.grpc.stub.StreamObserver<com.ksn.core.protobuf.AddressBookProto.AddressBook> responseObserver);
  }

  @io.grpc.ExperimentalApi
  public static abstract class AbstractAddressBookService implements AddressBookService, io.grpc.BindableService {

    @java.lang.Override
    public void showAddressBook(com.ksn.core.protobuf.AddressBookProto.AddressBook request,
        io.grpc.stub.StreamObserver<com.ksn.core.protobuf.AddressBookProto.AddressBook> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_SHOW_ADDRESS_BOOK, responseObserver);
    }

    @java.lang.Override public io.grpc.ServerServiceDefinition bindService() {
      return AddressBookServiceGrpc.bindService(this);
    }
  }

  /**
   */
  public static interface AddressBookServiceBlockingClient {

    /**
     */
    public com.ksn.core.protobuf.AddressBookProto.AddressBook showAddressBook(com.ksn.core.protobuf.AddressBookProto.AddressBook request);
  }

  /**
   */
  public static interface AddressBookServiceFutureClient {

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.ksn.core.protobuf.AddressBookProto.AddressBook> showAddressBook(
        com.ksn.core.protobuf.AddressBookProto.AddressBook request);
  }

  public static class AddressBookServiceStub extends io.grpc.stub.AbstractStub<AddressBookServiceStub>
      implements AddressBookService {
    private AddressBookServiceStub(io.grpc.Channel channel) {
      super(channel);
    }

    private AddressBookServiceStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected AddressBookServiceStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new AddressBookServiceStub(channel, callOptions);
    }

    @java.lang.Override
    public void showAddressBook(com.ksn.core.protobuf.AddressBookProto.AddressBook request,
        io.grpc.stub.StreamObserver<com.ksn.core.protobuf.AddressBookProto.AddressBook> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_SHOW_ADDRESS_BOOK, getCallOptions()), request, responseObserver);
    }
  }

  public static class AddressBookServiceBlockingStub extends io.grpc.stub.AbstractStub<AddressBookServiceBlockingStub>
      implements AddressBookServiceBlockingClient {
    private AddressBookServiceBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private AddressBookServiceBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected AddressBookServiceBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new AddressBookServiceBlockingStub(channel, callOptions);
    }

    @java.lang.Override
    public com.ksn.core.protobuf.AddressBookProto.AddressBook showAddressBook(com.ksn.core.protobuf.AddressBookProto.AddressBook request) {
      return blockingUnaryCall(
          getChannel(), METHOD_SHOW_ADDRESS_BOOK, getCallOptions(), request);
    }
  }

  public static class AddressBookServiceFutureStub extends io.grpc.stub.AbstractStub<AddressBookServiceFutureStub>
      implements AddressBookServiceFutureClient {
    private AddressBookServiceFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private AddressBookServiceFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected AddressBookServiceFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new AddressBookServiceFutureStub(channel, callOptions);
    }

    @java.lang.Override
    public com.google.common.util.concurrent.ListenableFuture<com.ksn.core.protobuf.AddressBookProto.AddressBook> showAddressBook(
        com.ksn.core.protobuf.AddressBookProto.AddressBook request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_SHOW_ADDRESS_BOOK, getCallOptions()), request);
    }
  }

  private static final int METHODID_SHOW_ADDRESS_BOOK = 0;

  private static class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final AddressBookService serviceImpl;
    private final int methodId;

    public MethodHandlers(AddressBookService serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_SHOW_ADDRESS_BOOK:
          serviceImpl.showAddressBook((com.ksn.core.protobuf.AddressBookProto.AddressBook) request,
              (io.grpc.stub.StreamObserver<com.ksn.core.protobuf.AddressBookProto.AddressBook>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  public static io.grpc.ServerServiceDefinition bindService(
      final AddressBookService serviceImpl) {
    return io.grpc.ServerServiceDefinition.builder(SERVICE_NAME)
        .addMethod(
          METHOD_SHOW_ADDRESS_BOOK,
          asyncUnaryCall(
            new MethodHandlers<
              com.ksn.core.protobuf.AddressBookProto.AddressBook,
              com.ksn.core.protobuf.AddressBookProto.AddressBook>(
                serviceImpl, METHODID_SHOW_ADDRESS_BOOK)))
        .build();
  }
}
