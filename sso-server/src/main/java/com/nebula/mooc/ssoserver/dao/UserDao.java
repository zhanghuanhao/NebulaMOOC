/*
 * @author Zhanghh
 * @date 2019/4/4
 */
package com.nebula.mooc.ssoserver.dao;

import com.nebula.mooc.core.entity.User;

public interface UserDao {

    int login(User user);
}
