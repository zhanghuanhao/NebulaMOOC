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

    public String login(LoginUser loginUser) {
        UserMessage.LoginUser request = TypeUtil.typeTransfer(loginUser);
        UserMessage.StringRet response = blockingStub.login(request);
        return response.getRet().equals("") ? null : response.getRet();
    }

    public boolean logout(String token) {
        UserMessage.StringRet request = UserMessage.StringRet
                .newBuilder().setRet(token).build();
        UserMessage.IntRet response = blockingStub.logout(request);
        return response.getRet() == Constant.SUCCESS_CODE;
    }

    public int register(LoginUser loginUser) {
        UserMessage.LoginUser request = TypeUtil.typeTransfer(loginUser);
        UserMessage.IntRet response = blockingStub.register(request);
        return response.getRet();
    }

    public boolean resetPassword(LoginUser loginUser) {
        UserMessage.LoginUser request = TypeUtil.typeTransfer(loginUser);
        UserMessage.IntRet response = blockingStub.resetPassword(request);
        return response.getRet() == Constant.SUCCESS_CODE;
    }

    public boolean loginCheck(String token) {
        UserMessage.StringRet request = UserMessage.StringRet
                .newBuilder().setRet(token).build();
        UserMessage.IntRet response = blockingStub.loginCheck(request);
        return response.getRet() == Constant.SUCCESS_CODE;
    }

    public boolean checkUserExist(String email) {
        UserMessage.StringRet request = UserMessage.StringRet
                .newBuilder().setRet(email).build();
        UserMessage.IntRet response = blockingStub.checkUserExist(request);
        return response.getRet() == Constant.SUCCESS_CODE;
    }

    public UserInfo getUserInfo(String token) {
        UserMessage.StringRet request = UserMessage.StringRet
                .newBuilder().setRet(token).build();
        UserMessage.UserInfo response = blockingStub.getUserInfo(request);
        // UserInfo为空，Id = 0
        return response.getId() == 0 ? null : TypeUtil.typeTransfer(response);
    }

    public boolean updateUser(UserInfo user) {
        UserMessage.UserInfo request = TypeUtil.typeTransfer(user);
        UserMessage.IntRet response = blockingStub.updateUser(request);
        return response.getRet() == Constant.SUCCESS_CODE;
    }

}
