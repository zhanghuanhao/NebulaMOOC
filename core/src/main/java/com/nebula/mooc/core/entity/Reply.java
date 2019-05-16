package com.nebula.mooc.core.entity;

import java.util.Date;

/**
 * Created by 15722 on 2019/4/22.
 */
public class Reply {
    private long id;
    private long postId;
    private long commentId;
    private long fromId;
    private long toId;
    private String fromName;
    private String toName;
    private String content;
    private int star;
    private Date createdTime;
    private Date ifStar;
    private String headimg;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    public long getPostId() {
        return postId;
    }

    public void setPostId(long postId) {
        this.postId = postId;
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

    public long getCommentId() {
        return commentId;
    }

    public void setCommentId(long commentId) {
        this.commentId = commentId;
    }

    public long getFromId() {
        return fromId;
    }

    public void setFromId(long fromId) {
        this.fromId = fromId;
    }

    public long getToId() {
        return toId;
    }

    public void setToId(long toId) {
        this.toId = toId;
    }

    public String getFromName() {
        return fromName;
    }

    public void setFromName(String fromName) {
        this.fromName = fromName;
    }

    public String getToName() {
        return toName;
    }

    public void setToName(String toName) {
        this.toName = toName;
    }

    public Date getIfStar() {
        return ifStar;
    }

    public void setIfStar(Date ifStar) {
        this.ifStar = ifStar;
    }

    public String getHeadimg() {
        return headimg;
    }

    public void setHeadimg(String headimg) {
        this.headimg = headimg;
    }
}
