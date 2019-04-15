/*
 * @author Zhanghh
 * @date 2019/4/4
 */
package com.nebula.mooc.ssoserver.dao;

import com.nebula.mooc.core.entity.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao {

    int login(User user);

    int register(User user);
}
