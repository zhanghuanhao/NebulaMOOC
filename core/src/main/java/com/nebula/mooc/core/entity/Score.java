/*
 * @author Zhanghh
 * @date 2019/4/13
 */
package com.nebula.mooc.core.entity;

public class Score {

    private long id;
    private long userId;
    private long courseId;
    private long score;

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

    public long getCourseId() {
        return courseId;
    }

    public void setCourseId(long courseId) {
        this.courseId = courseId;
    }

    public long getScore() {
        return score;
    }

    public void setScore(long score) {
        this.score = score;
    }

}
