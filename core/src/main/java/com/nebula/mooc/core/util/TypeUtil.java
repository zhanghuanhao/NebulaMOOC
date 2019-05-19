/*
 * @author Zhanghh
 * @date 2019/5/12
 */
package com.nebula.mooc.core.util;

import com.nebula.mooc.core.UserMessage;
import com.nebula.mooc.core.entity.LoginUser;
import com.nebula.mooc.core.entity.UserInfo;

/**
 * Protobuf数据格式和pojo格式的转换工具
 */
public class TypeUtil {
    public static LoginUser typeTransfer(UserMessage.LoginUser loginUser) {
        LoginUser newLoginUser = new LoginUser();
        newLoginUser.setUsername(loginUser.getUsername());
        newLoginUser.setPassword(loginUser.getPassword());
        newLoginUser.setCode(loginUser.getCode());
        newLoginUser.setNickname(loginUser.getNickname());
        return newLoginUser;
    }

    public static UserInfo typeTransfer(UserMessage.UserInfo userInfo) {
        UserInfo newUserInfo = new UserInfo();
        newUserInfo.setId(userInfo.getId());
        newUserInfo.setNickName(userInfo.getNickName());
        newUserInfo.setHeadUrl(userInfo.getHeadUrl());
        newUserInfo.setEmail(userInfo.getEmail());
        newUserInfo.setAge(userInfo.getAge());
        newUserInfo.setSex(userInfo.getSex());
        return newUserInfo;
    }

    public static UserMessage.LoginUser typeTransfer(LoginUser loginUser) {
        return UserMessage.LoginUser.newBuilder()
                .setUsername(loginUser.getUsername())
                .setPassword(loginUser.getPassword())
                .setCode(loginUser.getCode())
                .setNickname(loginUser.getNickname())
                .build();
    }

    public static UserMessage.UserInfo typeTransfer(UserInfo userInfo) {
        return UserMessage.UserInfo.newBuilder()
                .setId(userInfo.getId())
                .setNickName(userInfo.getNickName())
                .setHeadUrl(userInfo.getHeadUrl())
                .setEmail(userInfo.getEmail())
                .setAge(userInfo.getAge())
                .setSex(userInfo.getSex())
                .build();
    }

}
