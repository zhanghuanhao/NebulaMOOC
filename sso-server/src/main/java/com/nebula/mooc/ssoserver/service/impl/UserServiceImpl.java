/*
 * @author Zhanghh
 * @date 2019/4/8
 */
package com.nebula.mooc.ssoserver.service.impl;

import com.nebula.mooc.core.entity.LoginUser;
import com.nebula.mooc.core.util.RedisUtil;
import com.nebula.mooc.ssoserver.dao.UserDao;
import com.nebula.mooc.ssoserver.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("UserService")
public class UserServiceImpl implements UserService {

    private final UserDao userDao;

    @Autowired
    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    public boolean loginCheck(String token) {
        if (token != null) {
            //2. 若token存在，检查其登录时间是否过期
            if (RedisUtil.exists(token)) {
                //3. 如果token未过期，延长有效期
                RedisUtil.expire(token);
                return true;
            }
        }
        return false;
    }

    public boolean login(String token, LoginUser loginUser) {
        if (loginUser == null || token == null) {
            return false;
        }
        //访问数据库
        boolean result = userDao.login(loginUser) > 0;
        if (result) {
            //成功登陆，添加到Redis缓存
            result = RedisUtil.setString(token, "");
        }
        return result;
    }

    public void logout(String token) {
        if (token == null)
            return;
        RedisUtil.del(token);
    }

}
