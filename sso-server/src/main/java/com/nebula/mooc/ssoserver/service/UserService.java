package com.nebula.mooc.ssoserver.service;

import com.nebula.mooc.core.entity.Return;
import com.nebula.mooc.ssoserver.core.model.UserInfo;

public interface UserService {

    Return<UserInfo> findUser(String username, String password);

}
