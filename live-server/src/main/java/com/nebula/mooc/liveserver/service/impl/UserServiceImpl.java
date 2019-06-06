/*
 * @author Zhanghh
 * @date 2019/5/13
 */
package com.nebula.mooc.liveserver.service.impl;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.nebula.mooc.core.Constant;
import com.nebula.mooc.core.UserMessage;
import com.nebula.mooc.core.entity.UserInfo;
import com.nebula.mooc.core.service.UserServiceGrpc;
import com.nebula.mooc.core.util.TypeUtil;
import com.nebula.mooc.liveserver.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service("UserService")
public class UserServiceImpl implements UserService {

    /**
     * 存用户信息的Cache
     * key: 用户Token
     * value: 用户信息
     */
    private static final Cache<String, UserInfo> users = CacheBuilder.newBuilder()
            .expireAfterAccess(1, TimeUnit.HOURS)
            .build();

    @Autowired
    private UserServiceGrpc.UserServiceBlockingStub blockingStub;

    /**
     * 检查是否已登录
     *
     * @param token 获取唯一标识
     * @return true: 已登录 false: 未登录
     */
    public boolean loginCheck(String token) {
        UserInfo userInfo = users.getIfPresent(token);
        if (userInfo != null) return true;
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
        UserInfo userInfo = users.getIfPresent(token);
        if (userInfo != null) return userInfo;
        UserMessage.StringRet request = UserMessage.StringRet
                .newBuilder().setRet(token).build();
        UserMessage.UserInfo response = blockingStub.getUserInfo(request);
        // UserInfo为空，Id = 0
        if (response.getId() != 0) {
            userInfo = TypeUtil.typeTransfer(response);
            users.put(token, userInfo);
        }
        return userInfo;
    }

}
