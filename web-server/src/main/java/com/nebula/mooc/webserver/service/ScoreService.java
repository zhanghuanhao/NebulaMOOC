/*
 * @author Zhanghh
 * @date 2019-05-14
 */
package com.nebula.mooc.webserver.service;

import com.nebula.mooc.core.entity.CourseScore;
import com.nebula.mooc.core.entity.PostScore;

public interface ScoreService {

    void updateCourseScore(CourseScore courseScore);

    void updatePostScore(PostScore postScore);

}
