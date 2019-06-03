/*
 * @author Zhanghh
 * @date 2019-05-14
 */
package com.nebula.mooc.webserver.dao;

import com.nebula.mooc.core.entity.CourseScore;
import com.nebula.mooc.core.entity.PostScore;
import org.springframework.stereotype.Repository;

@Repository
public interface ScoreDao {

    int checkCourse(CourseScore courseScore);

    int viewCourse(CourseScore courseScore);

    int incrCourse(CourseScore courseScore);

    int decrCourse(CourseScore courseScore);

    int checkPost(PostScore postScore);

    int viewPost(PostScore postScore);

    int incrPost(PostScore postScore);

    int decrPost(PostScore postScore);

}
