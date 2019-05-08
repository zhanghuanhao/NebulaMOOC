/*
 * @author Zhanghh
 * @date 2019/4/4
 */
package com.nebula.mooc.ssoserver.dao;

import com.nebula.mooc.core.entity.LoginUser;
import com.nebula.mooc.core.entity.UserInfo;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao {

    UserInfo login(LoginUser loginUser);

    int register(LoginUser loginUser);

    int resetPassword(LoginUser loginUser);

    int checkuser(LoginUser loginUser);
}
