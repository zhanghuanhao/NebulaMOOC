/*
 * @author Zhanghh
 * @date 2019/4/8
 */
package com.nebula.mooc.ssoserver.service.impl;

import com.nebula.mooc.core.entity.LoginUser;
import com.nebula.mooc.core.util.RedisUtil;
import com.nebula.mooc.ssoserver.dao.UserDao;
import com.nebula.mooc.ssoserver.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("UserService")
public class UserServiceImpl implements UserService {

    @Resource
    private UserDao userDao;

    public boolean loginCheck(String sessionId) {
        if (sessionId != null) {
            //2. 若sessionId存在，检查其登录时间是否过期
            if (RedisUtil.exists(sessionId)) {
                //3. 如果session未过期，延长有效期
                RedisUtil.expire(sessionId);
                return true;
            }
        }
        return false;
    }

    public boolean login(String sessionId, LoginUser loginUser) {
        if (loginUser == null || sessionId == null) {
            return false;
        }
        //访问数据库
        boolean result = userDao.login(loginUser) > 0;
        if (result) {
            //成功登陆，添加到Redis缓存
            result = RedisUtil.setString(sessionId, "");
        }
        return result;
    }

    public void logout(String sessionId) {
        if (sessionId == null)
            return;
        RedisUtil.del(sessionId);
    }

}
