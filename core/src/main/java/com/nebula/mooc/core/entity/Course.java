/*
 * @author Zhanghh
 * @date 2019/4/13
 */
package com.nebula.mooc.core.entity;

import java.util.Date;
import java.util.List;

public class Course {

    private long id;
    private String kindName;
    private String title;
    private String introduction;
    private Date createdTime;
    private int like;
    private int star;
    private String courseHeadUrl;
    private String userNickName;
    private String userHeadUrl;
    private boolean ifStar;
    private boolean ifLike;
    private List<CourseChapter> courseChapterList;
    private List<CourseComment> courseCommentList;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getKindName() {
        return kindName;
    }

    public void setKindName(String kindName) {
        this.kindName = kindName;
    }

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

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public int getLike() {
        return like;
    }

    public void setLike(int like) {
        this.like = like;
    }

    public int getStar() {
        return star;
    }

    public void setStar(int star) {
        this.star = star;
    }

    public String getCourseHeadUrl() {
        return courseHeadUrl;
    }

    public void setCourseHeadUrl(String courseHeadUrl) {
        this.courseHeadUrl = courseHeadUrl;
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

    public boolean isIfStar() {
        return ifStar;
    }

    public void setIfStar(boolean ifStar) {
        this.ifStar = ifStar;
    }

    public boolean isIfLike() {
        return ifLike;
    }

    public void setIfLike(boolean ifLike) {
        this.ifLike = ifLike;
    }

    public List<CourseChapter> getCourseChapterList() {
        return courseChapterList;
    }

    public void setCourseChapterList(List<CourseChapter> courseChapterList) {
        this.courseChapterList = courseChapterList;
    }

    public List<CourseComment> getCourseCommentList() {
        return courseCommentList;
    }

    public void setCourseCommentList(List<CourseComment> courseCommentList) {
        this.courseCommentList = courseCommentList;
    }

}
