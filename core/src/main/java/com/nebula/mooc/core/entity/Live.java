/*
 * @author Zhanghh
 * @date 2019/6/2
 */
package com.nebula.mooc.core.entity;

import java.util.Date;

public class Live {

    private String title;
    private String introduction;
    private String liveToken;
    private UserInfo userInfo;
    private Date createdTime;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getLiveToken() {
        return liveToken;
    }

    public void setLiveToken(String liveToken) {
        this.liveToken = liveToken;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

}
