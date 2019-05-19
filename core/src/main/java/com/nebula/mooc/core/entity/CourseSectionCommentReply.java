/*
 * @author Zhanghh
 * @date 2019/5/19
 */
package com.nebula.mooc.core.entity;

import java.util.Date;

public class CourseSectionCommentReply {

    private long id;
    private long commentId;
    private String content;
    private long fromId;
    private long toId;
    private Date createdTime;
    private String fromUserNickName;
    private String fromUserHeadUrl;
    private String toUserNickName;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getCommentId() {
        return commentId;
    }

    public void setCommentId(long commentId) {
        this.commentId = commentId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public String getFromUserNickName() {
        return fromUserNickName;
    }

    public void setFromUserNickName(String fromUserNickName) {
        this.fromUserNickName = fromUserNickName;
    }

    public String getFromUserHeadUrl() {
        return fromUserHeadUrl;
    }

    public void setFromUserHeadUrl(String fromUserHeadUrl) {
        this.fromUserHeadUrl = fromUserHeadUrl;
    }

    public String getToUserNickName() {
        return toUserNickName;
    }

    public void setToUserNickName(String toUserNickName) {
        this.toUserNickName = toUserNickName;
    }

}
