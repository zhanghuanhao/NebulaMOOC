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

import java.util.Date;

@Service("LiveService")
public class LiveServiceImpl implements LiveService {

    public String newLive(UserInfo userInfo, Live live) {
        live.setUserInfo(userInfo);
        live.setCreatedTime(new Date());
        return LiveManager.newLive(userInfo.getId(), live);
    }

    public Object[] getLiveList() {
        return LiveManager.getList();
    }

    public Live getLive(long userId) {
        return LiveManager.getLive(userId);
    }

    public String getLiveToken(long userId) {
        return LiveManager.getLiveToken(userId);
    }

    public boolean checkToken(long userId, String liveToken) {
        return LiveManager.checkToken(userId, liveToken);
    }

}
