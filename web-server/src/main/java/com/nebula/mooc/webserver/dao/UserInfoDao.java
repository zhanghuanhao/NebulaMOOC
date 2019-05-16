/*
 * @author Zhanghh
 * @date 2019/4/4
 */
package com.nebula.mooc.webserver.dao;

import com.nebula.mooc.core.entity.UserInfo;
import org.springframework.stereotype.Repository;

@Repository
public interface UserInfoDao {

    UserInfo getUserInfo(UserInfo userInfo);
}
