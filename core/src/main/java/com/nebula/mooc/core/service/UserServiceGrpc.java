/*
 * @author Zhanghh
 * @date 2019/4/8
 */
package com.nebula.mooc.core.service;

import com.nebula.mooc.core.UserMessage;

import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;

@javax.annotation.Generated(
        value = "by gRPC proto compiler (version 1.22.0-SNAPSHOT)",
        comments = "Source: UserMessage.proto")
public final class UserServiceGrpc {

    private UserServiceGrpc() {
    }

    public static final String SERVICE_NAME = "UserService";

    // Static method descriptors that strictly reflect the proto.
    private static volatile io.grpc.MethodDescriptor<UserMessage.LoginUser,
            UserMessage.StringRet> getLoginMethod;

    @io.grpc.stub.annotations.RpcMethod(
            fullMethodName = SERVICE_NAME + '/' + "login",
            requestType = UserMessage.LoginUser.class,
            responseType = UserMessage.StringRet.class,
            methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
    public static io.grpc.MethodDescriptor<UserMessage.LoginUser,
            UserMessage.StringRet> getLoginMethod() {
        io.grpc.MethodDescriptor<UserMessage.LoginUser, UserMessage.StringRet> getLoginMethod;
        if ((getLoginMethod = UserServiceGrpc.getLoginMethod) == null) {
            synchronized (UserServiceGrpc.class) {
                if ((getLoginMethod = UserServiceGrpc.getLoginMethod) == null) {
                    UserServiceGrpc.getLoginMethod = getLoginMethod =
                            io.grpc.MethodDescriptor.<UserMessage.LoginUser, UserMessage.StringRet>newBuilder()
                                    .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
                                    .setFullMethodName(generateFullMethodName(
                                            "UserService", "login"))
                                    .setSampledToLocalTracing(true)
                                    .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                                            UserMessage.LoginUser.getDefaultInstance()))
                                    .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                                            UserMessage.StringRet.getDefaultInstance()))
                                    .setSchemaDescriptor(new UserServiceMethodDescriptorSupplier("login"))
                                    .build();
                }
            }
        }
        return getLoginMethod;
    }

    private static volatile io.grpc.MethodDescriptor<UserMessage.StringRet,
            UserMessage.IntRet> getLogoutMethod;

    @io.grpc.stub.annotations.RpcMethod(
            fullMethodName = SERVICE_NAME + '/' + "logout",
            requestType = UserMessage.StringRet.class,
            responseType = UserMessage.IntRet.class,
            methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
    public static io.grpc.MethodDescriptor<UserMessage.StringRet,
            UserMessage.IntRet> getLogoutMethod() {
        io.grpc.MethodDescriptor<UserMessage.StringRet, UserMessage.IntRet> getLogoutMethod;
        if ((getLogoutMethod = UserServiceGrpc.getLogoutMethod) == null) {
            synchronized (UserServiceGrpc.class) {
                if ((getLogoutMethod = UserServiceGrpc.getLogoutMethod) == null) {
                    UserServiceGrpc.getLogoutMethod = getLogoutMethod =
                            io.grpc.MethodDescriptor.<UserMessage.StringRet, UserMessage.IntRet>newBuilder()
                                    .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
                                    .setFullMethodName(generateFullMethodName(
                                            "UserService", "logout"))
                                    .setSampledToLocalTracing(true)
                                    .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                                            UserMessage.StringRet.getDefaultInstance()))
                                    .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                                            UserMessage.IntRet.getDefaultInstance()))
                                    .setSchemaDescriptor(new UserServiceMethodDescriptorSupplier("logout"))
                                    .build();
                }
            }
        }
        return getLogoutMethod;
    }

    private static volatile io.grpc.MethodDescriptor<UserMessage.LoginUser,
            UserMessage.IntRet> getRegisterMethod;

    @io.grpc.stub.annotations.RpcMethod(
            fullMethodName = SERVICE_NAME + '/' + "register",
            requestType = UserMessage.LoginUser.class,
            responseType = UserMessage.IntRet.class,
            methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
    public static io.grpc.MethodDescriptor<UserMessage.LoginUser,
            UserMessage.IntRet> getRegisterMethod() {
        io.grpc.MethodDescriptor<UserMessage.LoginUser, UserMessage.IntRet> getRegisterMethod;
        if ((getRegisterMethod = UserServiceGrpc.getRegisterMethod) == null) {
            synchronized (UserServiceGrpc.class) {
                if ((getRegisterMethod = UserServiceGrpc.getRegisterMethod) == null) {
                    UserServiceGrpc.getRegisterMethod = getRegisterMethod =
                            io.grpc.MethodDescriptor.<UserMessage.LoginUser, UserMessage.IntRet>newBuilder()
                                    .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
                                    .setFullMethodName(generateFullMethodName(
                                            "UserService", "register"))
                                    .setSampledToLocalTracing(true)
                                    .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                                            UserMessage.LoginUser.getDefaultInstance()))
                                    .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                                            UserMessage.IntRet.getDefaultInstance()))
                                    .setSchemaDescriptor(new UserServiceMethodDescriptorSupplier("register"))
                                    .build();
                }
            }
        }
        return getRegisterMethod;
    }

    private static volatile io.grpc.MethodDescriptor<UserMessage.LoginUser,
            UserMessage.IntRet> getResetPasswordMethod;

    @io.grpc.stub.annotations.RpcMethod(
            fullMethodName = SERVICE_NAME + '/' + "resetPassword",
            requestType = UserMessage.LoginUser.class,
            responseType = UserMessage.IntRet.class,
            methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
    public static io.grpc.MethodDescriptor<UserMessage.LoginUser,
            UserMessage.IntRet> getResetPasswordMethod() {
        io.grpc.MethodDescriptor<UserMessage.LoginUser, UserMessage.IntRet> getResetPasswordMethod;
        if ((getResetPasswordMethod = UserServiceGrpc.getResetPasswordMethod) == null) {
            synchronized (UserServiceGrpc.class) {
                if ((getResetPasswordMethod = UserServiceGrpc.getResetPasswordMethod) == null) {
                    UserServiceGrpc.getResetPasswordMethod = getResetPasswordMethod =
                            io.grpc.MethodDescriptor.<UserMessage.LoginUser, UserMessage.IntRet>newBuilder()
                                    .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
                                    .setFullMethodName(generateFullMethodName(
                                            "UserService", "resetPassword"))
                                    .setSampledToLocalTracing(true)
                                    .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                                            UserMessage.LoginUser.getDefaultInstance()))
                                    .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                                            UserMessage.IntRet.getDefaultInstance()))
                                    .setSchemaDescriptor(new UserServiceMethodDescriptorSupplier("resetPassword"))
                                    .build();
                }
            }
        }
        return getResetPasswordMethod;
    }

    private static volatile io.grpc.MethodDescriptor<UserMessage.StringRet,
            UserMessage.IntRet> getLoginCheckMethod;

    @io.grpc.stub.annotations.RpcMethod(
            fullMethodName = SERVICE_NAME + '/' + "loginCheck",
            requestType = UserMessage.StringRet.class,
            responseType = UserMessage.IntRet.class,
            methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
    public static io.grpc.MethodDescriptor<UserMessage.StringRet,
            UserMessage.IntRet> getLoginCheckMethod() {
        io.grpc.MethodDescriptor<UserMessage.StringRet, UserMessage.IntRet> getLoginCheckMethod;
        if ((getLoginCheckMethod = UserServiceGrpc.getLoginCheckMethod) == null) {
            synchronized (UserServiceGrpc.class) {
                if ((getLoginCheckMethod = UserServiceGrpc.getLoginCheckMethod) == null) {
                    UserServiceGrpc.getLoginCheckMethod = getLoginCheckMethod =
                            io.grpc.MethodDescriptor.<UserMessage.StringRet, UserMessage.IntRet>newBuilder()
                                    .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
                                    .setFullMethodName(generateFullMethodName(
                                            "UserService", "loginCheck"))
                                    .setSampledToLocalTracing(true)
                                    .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                                            UserMessage.StringRet.getDefaultInstance()))
                                    .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                                            UserMessage.IntRet.getDefaultInstance()))
                                    .setSchemaDescriptor(new UserServiceMethodDescriptorSupplier("loginCheck"))
                                    .build();
                }
            }
        }
        return getLoginCheckMethod;
    }

    private static volatile io.grpc.MethodDescriptor<UserMessage.StringRet,
            UserMessage.IntRet> getCheckUserExistMethod;

    @io.grpc.stub.annotations.RpcMethod(
            fullMethodName = SERVICE_NAME + '/' + "checkUserExist",
            requestType = UserMessage.StringRet.class,
            responseType = UserMessage.IntRet.class,
            methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
    public static io.grpc.MethodDescriptor<UserMessage.StringRet,
            UserMessage.IntRet> getCheckUserExistMethod() {
        io.grpc.MethodDescriptor<UserMessage.StringRet, UserMessage.IntRet> getCheckUserExistMethod;
        if ((getCheckUserExistMethod = UserServiceGrpc.getCheckUserExistMethod) == null) {
            synchronized (UserServiceGrpc.class) {
                if ((getCheckUserExistMethod = UserServiceGrpc.getCheckUserExistMethod) == null) {
                    UserServiceGrpc.getCheckUserExistMethod = getCheckUserExistMethod =
                            io.grpc.MethodDescriptor.<UserMessage.StringRet, UserMessage.IntRet>newBuilder()
                                    .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
                                    .setFullMethodName(generateFullMethodName(
                                            "UserService", "checkUserExist"))
                                    .setSampledToLocalTracing(true)
                                    .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                                            UserMessage.StringRet.getDefaultInstance()))
                                    .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                                            UserMessage.IntRet.getDefaultInstance()))
                                    .setSchemaDescriptor(new UserServiceMethodDescriptorSupplier("checkUserExist"))
                                    .build();
                }
            }
        }
        return getCheckUserExistMethod;
    }

    private static volatile io.grpc.MethodDescriptor<UserMessage.StringRet,
            UserMessage.UserInfo> getGetUserInfoMethod;

    @io.grpc.stub.annotations.RpcMethod(
            fullMethodName = SERVICE_NAME + '/' + "getUserInfo",
            requestType = UserMessage.StringRet.class,
            responseType = UserMessage.UserInfo.class,
            methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
    public static io.grpc.MethodDescriptor<UserMessage.StringRet,
            UserMessage.UserInfo> getGetUserInfoMethod() {
        io.grpc.MethodDescriptor<UserMessage.StringRet, UserMessage.UserInfo> getGetUserInfoMethod;
        if ((getGetUserInfoMethod = UserServiceGrpc.getGetUserInfoMethod) == null) {
            synchronized (UserServiceGrpc.class) {
                if ((getGetUserInfoMethod = UserServiceGrpc.getGetUserInfoMethod) == null) {
                    UserServiceGrpc.getGetUserInfoMethod = getGetUserInfoMethod =
                            io.grpc.MethodDescriptor.<UserMessage.StringRet, UserMessage.UserInfo>newBuilder()
                                    .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
                                    .setFullMethodName(generateFullMethodName(
                                            "UserService", "getUserInfo"))
                                    .setSampledToLocalTracing(true)
                                    .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                                            UserMessage.StringRet.getDefaultInstance()))
                                    .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                                            UserMessage.UserInfo.getDefaultInstance()))
                                    .setSchemaDescriptor(new UserServiceMethodDescriptorSupplier("getUserInfo"))
                                    .build();
                }
            }
        }
        return getGetUserInfoMethod;
    }

    private static volatile io.grpc.MethodDescriptor<UserMessage.UserInfo,
            UserMessage.StringRet> getUpdateUserMethod;

    @io.grpc.stub.annotations.RpcMethod(
            fullMethodName = SERVICE_NAME + '/' + "updateUser",
            requestType = UserMessage.UserInfo.class,
            responseType = UserMessage.StringRet.class,
            methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
    public static io.grpc.MethodDescriptor<UserMessage.UserInfo,
            UserMessage.StringRet> getUpdateUserMethod() {
        io.grpc.MethodDescriptor<UserMessage.UserInfo, UserMessage.StringRet> getUpdateUserMethod;
        if ((getUpdateUserMethod = UserServiceGrpc.getUpdateUserMethod) == null) {
            synchronized (UserServiceGrpc.class) {
                if ((getUpdateUserMethod = UserServiceGrpc.getUpdateUserMethod) == null) {
                    UserServiceGrpc.getUpdateUserMethod = getUpdateUserMethod =
                            io.grpc.MethodDescriptor.<UserMessage.UserInfo, UserMessage.StringRet>newBuilder()
                                    .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
                                    .setFullMethodName(generateFullMethodName(
                                            "UserService", "updateUser"))
                                    .setSampledToLocalTracing(true)
                                    .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                                            UserMessage.UserInfo.getDefaultInstance()))
                                    .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                                            UserMessage.StringRet.getDefaultInstance()))
                                    .setSchemaDescriptor(new UserServiceMethodDescriptorSupplier("updateUser"))
                                    .build();
                }
            }
        }
        return getUpdateUserMethod;
    }

    /**
     * Creates a new async stub that supports all call types for the service
     */
    public static UserServiceStub newStub(io.grpc.Channel channel) {
        return new UserServiceStub(channel);
    }

    /**
     * Creates a new blocking-style stub that supports unary and streaming output calls on the service
     */
    public static UserServiceBlockingStub newBlockingStub(
            io.grpc.Channel channel) {
        return new UserServiceBlockingStub(channel);
    }

    /**
     * Creates a new ListenableFuture-style stub that supports unary calls on the service
     */
    public static UserServiceFutureStub newFutureStub(
            io.grpc.Channel channel) {
        return new UserServiceFutureStub(channel);
    }

    /**
     *
     */
    public static abstract class UserServiceImplBase implements io.grpc.BindableService {

        /**
         *
         */
        public void login(UserMessage.LoginUser request,
                          io.grpc.stub.StreamObserver<UserMessage.StringRet> responseObserver) {
            asyncUnimplementedUnaryCall(getLoginMethod(), responseObserver);
        }

        /**
         *
         */
        public void logout(UserMessage.StringRet request,
                           io.grpc.stub.StreamObserver<UserMessage.IntRet> responseObserver) {
            asyncUnimplementedUnaryCall(getLogoutMethod(), responseObserver);
        }

        /**
         *
         */
        public void register(UserMessage.LoginUser request,
                             io.grpc.stub.StreamObserver<UserMessage.IntRet> responseObserver) {
            asyncUnimplementedUnaryCall(getRegisterMethod(), responseObserver);
        }

        /**
         *
         */
        public void resetPassword(UserMessage.LoginUser request,
                                  io.grpc.stub.StreamObserver<UserMessage.IntRet> responseObserver) {
            asyncUnimplementedUnaryCall(getResetPasswordMethod(), responseObserver);
        }

        /**
         *
         */
        public void loginCheck(UserMessage.StringRet request,
                               io.grpc.stub.StreamObserver<UserMessage.IntRet> responseObserver) {
            asyncUnimplementedUnaryCall(getLoginCheckMethod(), responseObserver);
        }

        /**
         *
         */
        public void checkUserExist(UserMessage.StringRet request,
                                   io.grpc.stub.StreamObserver<UserMessage.IntRet> responseObserver) {
            asyncUnimplementedUnaryCall(getCheckUserExistMethod(), responseObserver);
        }

        /**
         *
         */
        public void getUserInfo(UserMessage.StringRet request,
                                io.grpc.stub.StreamObserver<UserMessage.UserInfo> responseObserver) {
            asyncUnimplementedUnaryCall(getGetUserInfoMethod(), responseObserver);
        }

        /**
         *
         */
        public void updateUser(UserMessage.UserInfo request,
                               io.grpc.stub.StreamObserver<UserMessage.StringRet> responseObserver) {
            asyncUnimplementedUnaryCall(getUpdateUserMethod(), responseObserver);
        }

        @java.lang.Override
        public final io.grpc.ServerServiceDefinition bindService() {
            return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
                    .addMethod(
                            getLoginMethod(),
                            asyncUnaryCall(
                                    new MethodHandlers<
                                            UserMessage.LoginUser,
                                            UserMessage.StringRet>(
                                            this, METHODID_LOGIN)))
                    .addMethod(
                            getLogoutMethod(),
                            asyncUnaryCall(
                                    new MethodHandlers<
                                            UserMessage.StringRet,
                                            UserMessage.IntRet>(
                                            this, METHODID_LOGOUT)))
                    .addMethod(
                            getRegisterMethod(),
                            asyncUnaryCall(
                                    new MethodHandlers<
                                            UserMessage.LoginUser,
                                            UserMessage.IntRet>(
                                            this, METHODID_REGISTER)))
                    .addMethod(
                            getResetPasswordMethod(),
                            asyncUnaryCall(
                                    new MethodHandlers<
                                            UserMessage.LoginUser,
                                            UserMessage.IntRet>(
                                            this, METHODID_RESET_PASSWORD)))
                    .addMethod(
                            getLoginCheckMethod(),
                            asyncUnaryCall(
                                    new MethodHandlers<
                                            UserMessage.StringRet,
                                            UserMessage.IntRet>(
                                            this, METHODID_LOGIN_CHECK)))
                    .addMethod(
                            getCheckUserExistMethod(),
                            asyncUnaryCall(
                                    new MethodHandlers<
                                            UserMessage.StringRet,
                                            UserMessage.IntRet>(
                                            this, METHODID_CHECK_USER_EXIST)))
                    .addMethod(
                            getGetUserInfoMethod(),
                            asyncUnaryCall(
                                    new MethodHandlers<
                                            UserMessage.StringRet,
                                            UserMessage.UserInfo>(
                                            this, METHODID_GET_USER_INFO)))
                    .addMethod(
                            getUpdateUserMethod(),
                            asyncUnaryCall(
                                    new MethodHandlers<
                                            UserMessage.UserInfo,
                                            UserMessage.StringRet>(
                                            this, METHODID_UPDATE_USER)))
                    .build();
        }
    }

    /**
     *
     */
    public static final class UserServiceStub extends io.grpc.stub.AbstractStub<UserServiceStub> {
        private UserServiceStub(io.grpc.Channel channel) {
            super(channel);
        }

        private UserServiceStub(io.grpc.Channel channel,
                                io.grpc.CallOptions callOptions) {
            super(channel, callOptions);
        }

        @java.lang.Override
        protected UserServiceStub build(io.grpc.Channel channel,
                                        io.grpc.CallOptions callOptions) {
            return new UserServiceStub(channel, callOptions);
        }

        /**
         *
         */
        public void login(UserMessage.LoginUser request,
                          io.grpc.stub.StreamObserver<UserMessage.StringRet> responseObserver) {
            asyncUnaryCall(
                    getChannel().newCall(getLoginMethod(), getCallOptions()), request, responseObserver);
        }

        /**
         *
         */
        public void logout(UserMessage.StringRet request,
                           io.grpc.stub.StreamObserver<UserMessage.IntRet> responseObserver) {
            asyncUnaryCall(
                    getChannel().newCall(getLogoutMethod(), getCallOptions()), request, responseObserver);
        }

        /**
         *
         */
        public void register(UserMessage.LoginUser request,
                             io.grpc.stub.StreamObserver<UserMessage.IntRet> responseObserver) {
            asyncUnaryCall(
                    getChannel().newCall(getRegisterMethod(), getCallOptions()), request, responseObserver);
        }

        /**
         *
         */
        public void resetPassword(UserMessage.LoginUser request,
                                  io.grpc.stub.StreamObserver<UserMessage.IntRet> responseObserver) {
            asyncUnaryCall(
                    getChannel().newCall(getResetPasswordMethod(), getCallOptions()), request, responseObserver);
        }

        /**
         *
         */
        public void loginCheck(UserMessage.StringRet request,
                               io.grpc.stub.StreamObserver<UserMessage.IntRet> responseObserver) {
            asyncUnaryCall(
                    getChannel().newCall(getLoginCheckMethod(), getCallOptions()), request, responseObserver);
        }

        /**
         *
         */
        public void checkUserExist(UserMessage.StringRet request,
                                   io.grpc.stub.StreamObserver<UserMessage.IntRet> responseObserver) {
            asyncUnaryCall(
                    getChannel().newCall(getCheckUserExistMethod(), getCallOptions()), request, responseObserver);
        }

        /**
         *
         */
        public void getUserInfo(UserMessage.StringRet request,
                                io.grpc.stub.StreamObserver<UserMessage.UserInfo> responseObserver) {
            asyncUnaryCall(
                    getChannel().newCall(getGetUserInfoMethod(), getCallOptions()), request, responseObserver);
        }

        /**
         *
         */
        public void updateUser(UserMessage.UserInfo request,
                               io.grpc.stub.StreamObserver<UserMessage.StringRet> responseObserver) {
            asyncUnaryCall(
                    getChannel().newCall(getUpdateUserMethod(), getCallOptions()), request, responseObserver);
        }
    }

    /**
     *
     */
    public static final class UserServiceBlockingStub extends io.grpc.stub.AbstractStub<UserServiceBlockingStub> {
        private UserServiceBlockingStub(io.grpc.Channel channel) {
            super(channel);
        }

        private UserServiceBlockingStub(io.grpc.Channel channel,
                                        io.grpc.CallOptions callOptions) {
            super(channel, callOptions);
        }

        @java.lang.Override
        protected UserServiceBlockingStub build(io.grpc.Channel channel,
                                                io.grpc.CallOptions callOptions) {
            return new UserServiceBlockingStub(channel, callOptions);
        }

        /**
         *
         */
        public UserMessage.StringRet login(UserMessage.LoginUser request) {
            return blockingUnaryCall(
                    getChannel(), getLoginMethod(), getCallOptions(), request);
        }

        /**
         *
         */
        public UserMessage.IntRet logout(UserMessage.StringRet request) {
            return blockingUnaryCall(
                    getChannel(), getLogoutMethod(), getCallOptions(), request);
        }

        /**
         *
         */
        public UserMessage.IntRet register(UserMessage.LoginUser request) {
            return blockingUnaryCall(
                    getChannel(), getRegisterMethod(), getCallOptions(), request);
        }

        /**
         *
         */
        public UserMessage.IntRet resetPassword(UserMessage.LoginUser request) {
            return blockingUnaryCall(
                    getChannel(), getResetPasswordMethod(), getCallOptions(), request);
        }

        /**
         *
         */
        public UserMessage.IntRet loginCheck(UserMessage.StringRet request) {
            return blockingUnaryCall(
                    getChannel(), getLoginCheckMethod(), getCallOptions(), request);
        }

        /**
         *
         */
        public UserMessage.IntRet checkUserExist(UserMessage.StringRet request) {
            return blockingUnaryCall(
                    getChannel(), getCheckUserExistMethod(), getCallOptions(), request);
        }

        /**
         *
         */
        public UserMessage.UserInfo getUserInfo(UserMessage.StringRet request) {
            return blockingUnaryCall(
                    getChannel(), getGetUserInfoMethod(), getCallOptions(), request);
        }

        /**
         *
         */
        public UserMessage.StringRet updateUser(UserMessage.UserInfo request) {
            return blockingUnaryCall(
                    getChannel(), getUpdateUserMethod(), getCallOptions(), request);
        }
    }

    /**
     *
     */
    public static final class UserServiceFutureStub extends io.grpc.stub.AbstractStub<UserServiceFutureStub> {
        private UserServiceFutureStub(io.grpc.Channel channel) {
            super(channel);
        }

        private UserServiceFutureStub(io.grpc.Channel channel,
                                      io.grpc.CallOptions callOptions) {
            super(channel, callOptions);
        }

        @java.lang.Override
        protected UserServiceFutureStub build(io.grpc.Channel channel,
                                              io.grpc.CallOptions callOptions) {
            return new UserServiceFutureStub(channel, callOptions);
        }

        /**
         *
         */
        public com.google.common.util.concurrent.ListenableFuture<UserMessage.StringRet> login(
                UserMessage.LoginUser request) {
            return futureUnaryCall(
                    getChannel().newCall(getLoginMethod(), getCallOptions()), request);
        }

        /**
         *
         */
        public com.google.common.util.concurrent.ListenableFuture<UserMessage.IntRet> logout(
                UserMessage.StringRet request) {
            return futureUnaryCall(
                    getChannel().newCall(getLogoutMethod(), getCallOptions()), request);
        }

        /**
         *
         */
        public com.google.common.util.concurrent.ListenableFuture<UserMessage.IntRet> register(
                UserMessage.LoginUser request) {
            return futureUnaryCall(
                    getChannel().newCall(getRegisterMethod(), getCallOptions()), request);
        }

        /**
         *
         */
        public com.google.common.util.concurrent.ListenableFuture<UserMessage.IntRet> resetPassword(
                UserMessage.LoginUser request) {
            return futureUnaryCall(
                    getChannel().newCall(getResetPasswordMethod(), getCallOptions()), request);
        }

        /**
         *
         */
        public com.google.common.util.concurrent.ListenableFuture<UserMessage.IntRet> loginCheck(
                UserMessage.StringRet request) {
            return futureUnaryCall(
                    getChannel().newCall(getLoginCheckMethod(), getCallOptions()), request);
        }

        /**
         *
         */
        public com.google.common.util.concurrent.ListenableFuture<UserMessage.IntRet> checkUserExist(
                UserMessage.StringRet request) {
            return futureUnaryCall(
                    getChannel().newCall(getCheckUserExistMethod(), getCallOptions()), request);
        }

        /**
         *
         */
        public com.google.common.util.concurrent.ListenableFuture<UserMessage.UserInfo> getUserInfo(
                UserMessage.StringRet request) {
            return futureUnaryCall(
                    getChannel().newCall(getGetUserInfoMethod(), getCallOptions()), request);
        }

        /**
         *
         */
        public com.google.common.util.concurrent.ListenableFuture<UserMessage.StringRet> updateUser(
                UserMessage.UserInfo request) {
            return futureUnaryCall(
                    getChannel().newCall(getUpdateUserMethod(), getCallOptions()), request);
        }
    }

    private static final int METHODID_LOGIN = 0;
    private static final int METHODID_LOGOUT = 1;
    private static final int METHODID_REGISTER = 2;
    private static final int METHODID_RESET_PASSWORD = 3;
    private static final int METHODID_LOGIN_CHECK = 4;
    private static final int METHODID_CHECK_USER_EXIST = 5;
    private static final int METHODID_GET_USER_INFO = 6;
    private static final int METHODID_UPDATE_USER = 7;

    private static final class MethodHandlers<Req, Resp> implements
            io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
            io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
            io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
            io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
        private final UserServiceImplBase serviceImpl;
        private final int methodId;

        MethodHandlers(UserServiceImplBase serviceImpl, int methodId) {
            this.serviceImpl = serviceImpl;
            this.methodId = methodId;
        }

        @java.lang.Override
        @java.lang.SuppressWarnings("unchecked")
        public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
            switch (methodId) {
                case METHODID_LOGIN:
                    serviceImpl.login((UserMessage.LoginUser) request,
                            (io.grpc.stub.StreamObserver<UserMessage.StringRet>) responseObserver);
                    break;
                case METHODID_LOGOUT:
                    serviceImpl.logout((UserMessage.StringRet) request,
                            (io.grpc.stub.StreamObserver<UserMessage.IntRet>) responseObserver);
                    break;
                case METHODID_REGISTER:
                    serviceImpl.register((UserMessage.LoginUser) request,
                            (io.grpc.stub.StreamObserver<UserMessage.IntRet>) responseObserver);
                    break;
                case METHODID_RESET_PASSWORD:
                    serviceImpl.resetPassword((UserMessage.LoginUser) request,
                            (io.grpc.stub.StreamObserver<UserMessage.IntRet>) responseObserver);
                    break;
                case METHODID_LOGIN_CHECK:
                    serviceImpl.loginCheck((UserMessage.StringRet) request,
                            (io.grpc.stub.StreamObserver<UserMessage.IntRet>) responseObserver);
                    break;
                case METHODID_CHECK_USER_EXIST:
                    serviceImpl.checkUserExist((UserMessage.StringRet) request,
                            (io.grpc.stub.StreamObserver<UserMessage.IntRet>) responseObserver);
                    break;
                case METHODID_GET_USER_INFO:
                    serviceImpl.getUserInfo((UserMessage.StringRet) request,
                            (io.grpc.stub.StreamObserver<UserMessage.UserInfo>) responseObserver);
                    break;
                case METHODID_UPDATE_USER:
                    serviceImpl.updateUser((UserMessage.UserInfo) request,
                            (io.grpc.stub.StreamObserver<UserMessage.StringRet>) responseObserver);
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

    private static abstract class UserServiceBaseDescriptorSupplier
            implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
        UserServiceBaseDescriptorSupplier() {
        }

        @java.lang.Override
        public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
            return UserMessage.getDescriptor();
        }

        @java.lang.Override
        public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
            return getFileDescriptor().findServiceByName("UserService");
        }
    }

    private static final class UserServiceFileDescriptorSupplier
            extends UserServiceBaseDescriptorSupplier {
        UserServiceFileDescriptorSupplier() {
        }
    }

    private static final class UserServiceMethodDescriptorSupplier
            extends UserServiceBaseDescriptorSupplier
            implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
        private final String methodName;

        UserServiceMethodDescriptorSupplier(String methodName) {
            this.methodName = methodName;
        }

        @java.lang.Override
        public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
            return getServiceDescriptor().findMethodByName(methodName);
        }
    }

    private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

    public static io.grpc.ServiceDescriptor getServiceDescriptor() {
        io.grpc.ServiceDescriptor result = serviceDescriptor;
        if (result == null) {
            synchronized (UserServiceGrpc.class) {
                result = serviceDescriptor;
                if (result == null) {
                    serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
                            .setSchemaDescriptor(new UserServiceFileDescriptorSupplier())
                            .addMethod(getLoginMethod())
                            .addMethod(getLogoutMethod())
                            .addMethod(getRegisterMethod())
                            .addMethod(getResetPasswordMethod())
                            .addMethod(getLoginCheckMethod())
                            .addMethod(getCheckUserExistMethod())
                            .addMethod(getGetUserInfoMethod())
                            .addMethod(getUpdateUserMethod())
                            .build();
                }
            }
        }
        return result;
    }
}
