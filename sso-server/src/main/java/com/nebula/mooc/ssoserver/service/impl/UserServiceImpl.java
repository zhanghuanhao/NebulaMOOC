/*
 * @author Zhanghh
 * @date 2019/4/8
 */
package com.nebula.mooc.ssoserver.service.impl;

import com.nebula.mooc.core.entity.User;
import com.nebula.mooc.core.util.RedisUtil;
import com.nebula.mooc.ssoserver.dao.UserDao;
import com.nebula.mooc.ssoserver.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("UserService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    private boolean checkUserNull(User user) {
        return user == null || user.getUsername() == null || user.getPassword() == null;
    }

    @Override
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

    @Override
    public boolean login(String token, User user) {
        if (token == null || checkUserNull(user)) return false;
        //访问数据库
        boolean result = userDao.login(user) > 0;
        if (result) {
            //成功登陆，添加到Redis缓存
            result = RedisUtil.setString(token, "");
        }
        return result;
    }

    @Override
    public void logout(String token) {
        if (token == null) return;
        RedisUtil.del(token);
    }

    @Override
    public boolean register(User user) {
        if (checkUserNull(user)) return false;
        return userDao.register(user) > 0;
    }

    @Override
    public boolean resetPassword(User user) {
        if (checkUserNull(user)) return false;
        return userDao.resetPassword(user) > 0;
    }
}
