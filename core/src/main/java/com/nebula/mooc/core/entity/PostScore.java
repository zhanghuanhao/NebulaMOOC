/*
 * @author Zhanghh
 * @date 2019/4/13
 */
package com.nebula.mooc.core.entity;

public class PostScore {

    private long userId;
    private long postId;
    private long score;

    public PostScore(long userId, long postId, long score) {
        this.userId = userId;
        this.postId = postId;
        this.score = score;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getCourseId() {
        return postId;
    }

    public void setCourseId(long postId) {
        this.postId = postId;
    }

    public long getScore() {
        return score;
    }

    public void setScore(long score) {
        this.score = score;
    }

}
