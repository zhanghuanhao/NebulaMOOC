/*
 * @author Zhanghh
 * @date 2019-05-14
 */
package com.nebula.mooc.webserver.service;

public interface ScoreService {

    void incrCourse(long userId, long courseId, int score);

    void decrCourse(long userId, long courseId, int score);

    void incrPost(long userId, long postId, int score);

    void decrPost(long userId, long postId, int score);

    void viewCourse(long userId, long courseId, int score);

    void viewPost(long userId, long postId, int score);

}
