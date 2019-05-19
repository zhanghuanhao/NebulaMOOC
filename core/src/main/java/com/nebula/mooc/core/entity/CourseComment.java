/*
 * @author Zhanghh
 * @date 2019/4/13
 */
package com.nebula.mooc.core.entity;

public class CourseComment {

    private long id;
    private long courseId;
    private String content;
    private int star;
    private boolean ifStar;
    private String userNickName;
    private String userHeadUrl;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getCourseId() {
        return courseId;
    }

    public void setCourseId(long courseId) {
        this.courseId = courseId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getStar() {
        return star;
    }

    public void setStar(int star) {
        this.star = star;
    }

    public boolean isIfStar() {
        return ifStar;
    }

    public void setIfStar(boolean ifStar) {
        this.ifStar = ifStar;
    }

    public String getUserNickName() {
        return userNickName;
    }

    public void setUserNickName(String userNickName) {
        this.userNickName = userNickName;
    }

    public String getUserHeadUrl() {
        return userHeadUrl;
    }

    public void setUserHeadUrl(String userHeadUrl) {
        this.userHeadUrl = userHeadUrl;
    }

}
