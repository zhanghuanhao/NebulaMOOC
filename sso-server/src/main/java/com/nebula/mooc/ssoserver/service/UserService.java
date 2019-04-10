package com.nebula.mooc.ssoserver.service;

import com.nebula.mooc.ssoserver.core.model.UserInfo;
import com.nebula.mooc.ssoserver.core.result.ReturnT;

public interface UserService {

    ReturnT<UserInfo> findUser(String username, String password);

}
