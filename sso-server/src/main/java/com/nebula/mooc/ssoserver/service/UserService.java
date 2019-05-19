/*
 * @author Zhanghh
 * @date 2019/5/12
 */
package com.nebula.mooc.ssoserver.service;

import com.nebula.mooc.core.Constant;
import com.nebula.mooc.core.UserMessage;
import com.nebula.mooc.core.entity.LoginUser;
import com.nebula.mooc.core.entity.UserInfo;
import com.nebula.mooc.core.service.UserServiceGrpc;
import com.nebula.mooc.core.util.TokenUtil;
import com.nebula.mooc.core.util.TypeUtil;
import com.nebula.mooc.ssoserver.dao.UserDao;
import com.nebula.mooc.ssoserver.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 继承Protobuf生成的文件，实现刚刚编写的RPC接口
 */
@Service("UserService")
public class UserService extends UserServiceGrpc.UserServiceImplBase {

    private static final UserMessage.IntRet successInt = UserMessage.IntRet
            .newBuilder().setRet(Constant.SUCCESS_CODE).build();
    private static final UserMessage.IntRet failedInt = UserMessage.IntRet
            .newBuilder().setRet(Constant.CLIENT_ERROR_CODE).build();
    private static final UserMessage.StringRet nullString = UserMessage.StringRet
            .newBuilder().setRet("").build();
    private static final UserMessage.UserInfo nullUserInfo = UserMessage.UserInfo
            .newBuilder().setId(0).setNickName("").setHeadUrl("").build();

    @Autowired
    private UserDao userDao;

    @Autowired
    private RedisUtil redisUtil;

    /**
     * 登陆，返回token
     *
     * @param request 用户信息
     */
    @Override
    public void login(UserMessage.LoginUser request,
                      io.grpc.stub.StreamObserver<UserMessage.StringRet> responseObserver) {
        UserMessage.StringRet result = nullString;
        //访问数据库
        LoginUser loginUser = TypeUtil.typeTransfer(request);
        UserInfo userInfo = userDao.login(loginUser);
        if (userInfo != null) {
            //成功登陆，生成token，并添加到Redis缓存
            String token = Constant.TOKEN + TokenUtil.generateToken(loginUser);
            if (redisUtil.setObject(token, userInfo))
                result = UserMessage.StringRet.newBuilder()
                        .setRet(token).build();
        }
        // 使用响应监视器的onNext方法返回
        responseObserver.onNext(result);
        // 使用onCompleted方法指定本次调用已经完成
        responseObserver.onCompleted();
    }

    /**
     * 注销，返回token
     *
     * @param request token
     */
    @Override
    public void logout(UserMessage.StringRet request,
                       io.grpc.stub.StreamObserver<UserMessage.IntRet> responseObserver) {
        UserMessage.IntRet result;
        String token = request.getRet();
        if (token.length() > 0 && redisUtil.del(token))
            result = successInt;
        else
            result = failedInt;
        responseObserver.onNext(result);
        responseObserver.onCompleted();
    }

    /**
     * 注册，返回Constant中状态码
     *
     * @param request 用户信息
     */
    @Override
    public void register(UserMessage.LoginUser request,
                         io.grpc.stub.StreamObserver<UserMessage.IntRet> responseObserver) {
        UserMessage.IntRet result = failedInt;
        LoginUser loginUser = TypeUtil.typeTransfer(request);
        // 判断是否已注册
        if (userDao.checkUserExists(loginUser.getUsername())) {
            result = UserMessage.IntRet.newBuilder()
                    .setRet(Constant.CLIENT_REGISTERED).build();
        } else {
            if (userDao.register(loginUser) > 0)
                result = successInt;
        }
        responseObserver.onNext(result);
        responseObserver.onCompleted();
    }

    /**
     * 重置密码，返回Constant中状态码
     *
     * @param request 用户信息
     */
    @Override
    public void resetPassword(UserMessage.LoginUser request,
                              io.grpc.stub.StreamObserver<UserMessage.IntRet> responseObserver) {
        UserMessage.IntRet result;
        LoginUser loginUser = TypeUtil.typeTransfer(request);
        if (userDao.resetPassword(loginUser) > 0)
            result = successInt;
        else
            result = failedInt;
        responseObserver.onNext(result);
        responseObserver.onCompleted();
    }

    /**
     * 检查是否已登录，返回Constant中状态码
     *
     * @param request token值
     */
    @Override
    public void loginCheck(UserMessage.StringRet request,
                           io.grpc.stub.StreamObserver<UserMessage.IntRet> responseObserver) {
        String token = request.getRet();
        UserMessage.IntRet result;
        // 1. 检查其登录时间是否过期
        // 2. 如果token未过期，延长有效期，返回用户信息
        if (token.length() > 0 && redisUtil.exists(token) && redisUtil.expire(token))
            result = successInt;
        else
            result = failedInt;
        responseObserver.onNext(result);
        responseObserver.onCompleted();
    }

    /**
     * 检查用户是否存在，返回Constant中状态码
     *
     * @param request 用户邮箱
     */
    @Override
    public void checkUserExist(UserMessage.StringRet request,
                               io.grpc.stub.StreamObserver<UserMessage.IntRet> responseObserver) {
        String email = request.getRet();
        UserMessage.IntRet result;
        if (email.length() > 0 && userDao.checkUserExists(email))
            result = successInt;
        else
            result = failedInt;
        responseObserver.onNext(result);
        responseObserver.onCompleted();
    }

    /**
     * 获得用户信息UserInfo，返回Constant中状态码
     *
     * @param request token
     */
    @Override
    public void getUserInfo(UserMessage.StringRet request,
                            io.grpc.stub.StreamObserver<UserMessage.UserInfo> responseObserver) {
        String token = request.getRet();
        UserMessage.UserInfo result = nullUserInfo;
        if (token.length() > 0) {
            UserInfo userInfo = (UserInfo) redisUtil.getObject(token);
            if (userInfo != null) {
                result = TypeUtil.typeTransfer(userInfo);
            }
        }
        responseObserver.onNext(result);
        responseObserver.onCompleted();
    }

    /**
     * 修改用户个人信息，返回Constant中状态码
     *
     * @param request user
     */
    public void updateUser(UserMessage.UserInfo request,
                           io.grpc.stub.StreamObserver<UserMessage.IntRet> responseObserver) {
        UserInfo userInfo = TypeUtil.typeTransfer(request);
        UserMessage.IntRet result;
        if (userDao.updateUser(userInfo) > 0)
            result = successInt;
        else
            result = failedInt;
        responseObserver.onNext(result);
        responseObserver.onCompleted();
    }

}
