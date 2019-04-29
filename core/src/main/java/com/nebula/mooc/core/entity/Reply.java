package com.nebula.mooc.core.entity;

import java.util.Date;

/**
 * Created by 15722 on 2019/4/22.
 */
public class Reply {
    private long id;
    private long userId;
    private long postId;
    private long fatherId;
    private int star;
    private String content;
    private Date createdTime;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }


    public long getPostId() {
        return postId;
    }

    public void setPostId(long postId) {
        this.postId = postId;
    }


    public void setFatherId(long fatherId) {
        this.fatherId = fatherId;
    }

    public long getFatherId() {
        return fatherId;
    }


    public void setStar(int star) {
        this.star = star;
    }

    public int getStar() {
        return star;
    }


    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }


    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

}
