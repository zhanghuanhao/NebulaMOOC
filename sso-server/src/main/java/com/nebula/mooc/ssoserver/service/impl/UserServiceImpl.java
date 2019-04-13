/*
 * @author Zhanghh
 * @date 2019/4/8
 */
package com.nebula.mooc.ssoserver.service.impl;

import com.nebula.mooc.core.entity.Constant;
import com.nebula.mooc.core.entity.LoginUser;
import com.nebula.mooc.ssoserver.dao.UserDao;
import com.nebula.mooc.ssoserver.service.UserService;
import com.nebula.mooc.ssoserver.util.CookieUtil;
import com.nebula.mooc.ssoserver.util.RedisUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Service("UserService")
public class UserServiceImpl implements UserService {

    @Resource
    private UserDao userDao;

    public boolean loginCheck(HttpServletRequest request) {
        // 1. 检查是否浏览器Cookie中是否有sessionId
        String sessionId = CookieUtil.get(request, Constant.SESSION_ID);
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

    public boolean login(HttpServletRequest request, HttpServletResponse response, LoginUser loginUser) {
        if (loginUser == null) {
            return false;
        }
        //访问数据库
        boolean result = userDao.login(loginUser) > 0;
        if (result) {
            //成功登陆
            String sessionId = request.getSession().getId();
            //设置Cookie
            CookieUtil.set(response, Constant.SESSION_ID, sessionId);
            //添加到Redis缓存
            result = RedisUtil.setString(sessionId, "");
        }
        return result;
    }

    public void logout(HttpServletRequest request, HttpServletResponse response) {
        String sessionId = CookieUtil.get(request, Constant.SESSION_ID);
        if (sessionId == null)
            return;
        CookieUtil.remove(request, response, Constant.SESSION_ID);
        RedisUtil.del(sessionId);
    }

}
