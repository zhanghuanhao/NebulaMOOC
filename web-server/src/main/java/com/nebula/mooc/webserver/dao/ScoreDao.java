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

    int insertCourseScore(CourseScore courseScore);

    int updateCourseScore(CourseScore courseScore);

    int insertPostScore(PostScore postScore);

    int updatePostScore(PostScore postScore);

}
