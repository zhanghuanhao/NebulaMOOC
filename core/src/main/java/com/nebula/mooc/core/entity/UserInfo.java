package com.nebula.mooc.core.entity;

/**
 * Created by 15722 on 2019/4/19.
 */

import java.io.Serializable;

public class UserInfo implements Serializable {
    public static final long serialVersionUID = 1L;

    private long id = 0;
    private String nickName = "";
    private String headUrl = "";
    private String email = "";
    private int age = 0;
    private int sex = 0;

    public String getHeadUrl() {
        return headUrl;
    }

    public void setHeadUrl(String headUrl) {
        this.headUrl = headUrl;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
