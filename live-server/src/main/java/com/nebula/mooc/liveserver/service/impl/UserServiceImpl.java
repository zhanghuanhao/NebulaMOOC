/*
 * @author Zhanghh
 * @date 2019/5/13
 */
package com.nebula.mooc.liveserver.service.impl;

import com.nebula.mooc.core.Constant;
import com.nebula.mooc.core.UserMessage;
import com.nebula.mooc.core.entity.UserInfo;
import com.nebula.mooc.core.service.UserServiceGrpc;
import com.nebula.mooc.core.util.TypeUtil;
import com.nebula.mooc.liveserver.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("UserService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserServiceGrpc.UserServiceBlockingStub blockingStub;

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
