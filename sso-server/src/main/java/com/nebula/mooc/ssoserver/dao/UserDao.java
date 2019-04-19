/*
 * @author Zhanghh
 * @date 2019/4/4
 */
package com.nebula.mooc.ssoserver.dao;

import com.nebula.mooc.core.entity.User;
import com.nebula.mooc.core.entity.UserInfo;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao {

    UserInfo login(User user);

    int register(User user);

    int resetPassword(User user);
}
