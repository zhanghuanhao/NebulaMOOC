/*
 * @author Zhanghh
 * @date 2019/5/13
 */
package com.nebula.mooc.liveserver.service.impl;

import com.nebula.mooc.core.entity.Live;
import com.nebula.mooc.core.entity.UserInfo;
import com.nebula.mooc.liveserver.core.LiveManager;
import com.nebula.mooc.liveserver.service.LiveService;
import org.springframework.stereotype.Service;

@Service("LiveService")
public class LiveServiceImpl implements LiveService {

    public String newLive(UserInfo userInfo, Live live) {
        live.setUserInfo(userInfo);
        return LiveManager.newLive(userInfo.getId(), live);
    }

    public Object[] getLiveList() {
        return LiveManager.getList();
    }


    public Live getMyLive(long userId) {
        return LiveManager.getLive(userId);
    }

    public void putUserInfo(String token, UserInfo userInfo) {
        LiveManager.putUserInfo(token, userInfo);
    }

    public UserInfo getUserInfo(String token) {
        return LiveManager.getUserInfo(token);
    }

    public boolean checkToken(String userToken, String liveToken) {
        UserInfo userInfo = getUserInfo(userToken);
        return userInfo != null && LiveManager.checkToken(userInfo.getId(), liveToken);
    }

}
