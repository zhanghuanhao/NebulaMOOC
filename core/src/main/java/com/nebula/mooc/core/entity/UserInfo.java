package com.nebula.mooc.core.entity;

/**
 * Created by 15722 on 2019/4/19.
 */

import java.io.Serializable;

public class UserInfo implements Serializable {
    public static final long serialVersionUID = 1L;

    private String nickName;
    //    private String headImg;
    private long id;

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

//    public String getHeadImg() {
//        return headImg;
//    }
//
//    public void setHeadImg(String headImg) {
//        this.headImg = headImg;
//    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
