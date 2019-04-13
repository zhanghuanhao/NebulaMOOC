/*
 * @author Zhanghh
 * @date 2019/4/8
 */
package com.nebula.mooc.ssoserver.service;

import com.nebula.mooc.core.entity.LoginUser;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface UserService {

    /**
     * 检查是否已登录
     *
     * @param request 请求
     * @return true: 已登录 false: 未登录
     */
    boolean loginCheck(HttpServletRequest request);

    /**
     * 登陆
     *
     * @param request   请求
     * @param response  回应
     * @param loginUser 登陆用户
     * @return true: 登陆成功
     */
    boolean login(HttpServletRequest request, HttpServletResponse response, LoginUser loginUser);

    /**
     * 注销
     *
     * @param request  请求
     * @param response 回应
     */
    void logout(HttpServletRequest request, HttpServletResponse response);
}
