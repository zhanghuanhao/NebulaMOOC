/*
 * @author Zhanghh
 * @date 2019/5/13
 */
package com.nebula.mooc.webserver.service.impl;

import com.nebula.mooc.core.Constant;
import com.nebula.mooc.core.UserMessage;
import com.nebula.mooc.core.entity.LoginUser;
import com.nebula.mooc.core.entity.UserInfo;
import com.nebula.mooc.core.service.UserServiceGrpc;
import com.nebula.mooc.core.util.TypeUtil;
import com.nebula.mooc.webserver.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("UserService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserServiceGrpc.UserServiceBlockingStub blockingStub;

    /**
     * 登陆
     *
     * @param loginUser 登陆的用户
     * @return token 获取唯一标识
     */
    public String login(LoginUser loginUser) {
        UserMessage.LoginUser request = TypeUtil.typeTransfer(loginUser);
        UserMessage.StringRet response = blockingStub.login(request);
        return response.getRet().equals("") ? null : response.getRet();
    }

    /**
     * 注销
     *
     * @param token 获取唯一标识
     */
    public boolean logout(String token) {
        UserMessage.StringRet request = UserMessage.StringRet
                .newBuilder().setRet(token).build();
        UserMessage.IntRet response = blockingStub.logout(request);
        return response.getRet() == Constant.SUCCESS_CODE;
    }

    /**
     * 注册
     *
     * @param loginUser 用户信息
     * @return 304 ->已注册 300 -> 失败 100 -> 成功
     */
    public int register(LoginUser loginUser) {
        UserMessage.LoginUser request = TypeUtil.typeTransfer(loginUser);
        UserMessage.IntRet response = blockingStub.register(request);
        return response.getRet();
    }

    /**
     * 重置密码
     *
     * @param loginUser 用户信息
     */
    public boolean resetPassword(LoginUser loginUser) {
        UserMessage.LoginUser request = TypeUtil.typeTransfer(loginUser);
        UserMessage.IntRet response = blockingStub.resetPassword(request);
        return response.getRet() == Constant.SUCCESS_CODE;
    }

    /**
     * 检查是否已登录
     *
     * @param token 获取唯一标识
     * @return true: 已登录 false: 未登录
     */
    public boolean loginCheck(String token) {
        UserMessage.StringRet request = UserMessage.StringRet
                .newBuilder().setRet(token).build();
        UserMessage.IntRet response = blockingStub.loginCheck(request);
        return response.getRet() == Constant.SUCCESS_CODE;
    }

    /**
     * 检查用户是否存在
     *
     * @param email 用户邮箱
     */
    public boolean checkUserExist(String email) {
        UserMessage.StringRet request = UserMessage.StringRet
                .newBuilder().setRet(email).build();
        UserMessage.IntRet response = blockingStub.checkUserExist(request);
        return response.getRet() == Constant.SUCCESS_CODE;
    }

    /**
     * 获得用户信息UserInfo
     *
     * @param token 获取唯一标识
     * @return 用户信息
     */
    public UserInfo getUserInfo(String token) {
        UserMessage.StringRet request = UserMessage.StringRet
                .newBuilder().setRet(token).build();
        UserMessage.UserInfo response = blockingStub.getUserInfo(request);
        // UserInfo为空，Id = 0
        return response.getId() == 0 ? null : TypeUtil.typeTransfer(response);
    }
}
