/*
 * @author Zhanghh
 * @date 2019-05-14
 */
package com.nebula.mooc.webserver.service.impl;

import com.nebula.mooc.core.entity.CourseScore;
import com.nebula.mooc.core.entity.PostScore;
import com.nebula.mooc.webserver.service.ScoreService;
import com.nebula.mooc.webserver.util.TaskUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("ScoreService")
public class ScoreServiceImpl implements ScoreService {

    @Autowired
    private TaskUtil taskUtil;

    public void incrCourse(long userId, long courseId, int score) {
        taskUtil.incrCourse(new CourseScore(userId, courseId, score));
    }

    public void decrCourse(long userId, long courseId, int score) {
        taskUtil.decrCourse(new CourseScore(userId, courseId, score));
    }

    public void incrPost(long userId, long postId, int score) {
        taskUtil.incrPost(new PostScore(userId, postId, score));
    }

    public void decrPost(long userId, long postId, int score) {
        taskUtil.decrPost(new PostScore(userId, postId, score));
    }

    public void viewCourse(long userId, long courseId, int score) {
        taskUtil.viewCourse(new CourseScore(userId, courseId, score));
    }

    public void viewPost(long userId, long postId, int score) {
        taskUtil.viewPost(new PostScore(userId, postId, score));
    }

}
