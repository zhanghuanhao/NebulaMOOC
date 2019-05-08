/*
 * @author Zhanghh
 * @date 2019/4/8
 */
package com.nebula.mooc.ssoserver.service;

import com.nebula.mooc.core.entity.LoginUser;
import com.nebula.mooc.core.entity.UserInfo;
import com.nebula.mooc.core.service.UserService;
import com.nebula.mooc.core.util.TokenUtil;
import com.nebula.mooc.ssoserver.dao.UserDao;
import com.nebula.mooc.ssoserver.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("UserService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    private boolean checkUserNull(LoginUser loginUser) {
        return loginUser == null || loginUser.getUsername() == null || loginUser.getPassword() == null;
    }

    @Override
    public String login(LoginUser loginUser) {
        if (checkUserNull(loginUser)) return null;
        //访问数据库
        UserInfo result = userDao.login(loginUser);
        if (result != null) {
            //成功登陆，生成token，并添加到Redis缓存
            String token = TokenUtil.generateToken(loginUser);
            RedisUtil.setObject(token, result);
            return token;
        }
        return null;
    }

    @Override
    public void logout(String token) {
        if (token == null) return;
        RedisUtil.del(token);
    }

    @Override
    public int register(LoginUser loginUser) {
        if (checkUserNull(loginUser)) return 0;
        try {
            userDao.register(loginUser);
        } catch (Exception e) {
            return -1;
        }
        return 1;
    }

    @Override
    public boolean resetPassword(LoginUser loginUser) {
        if (checkUserNull(loginUser)) return false;
        return userDao.resetPassword(loginUser) > 0;
    }

    @Override
    public boolean loginCheck(String token) {
        if (token != null) {
            //1. 若token存在，检查其登录时间是否过期
            if (RedisUtil.exists(token)) {
                //2. 如果token未过期，延长有效期，返回用户信息
                RedisUtil.expire(token);
                return true;
            }
        }
        return false;
    }

    @Override
    public UserInfo getUserInfo(String token) {
        if (token != null) {
            return (UserInfo) RedisUtil.getObject(token);
        }
        return null;
    }

    @Override
    public boolean checkUser(String email) {
        LoginUser loginUser = new LoginUser();
        loginUser.setUsername(email);
        return userDao.checkUser(loginUser);
    }
}
