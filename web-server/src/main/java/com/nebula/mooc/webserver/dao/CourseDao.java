/*
 * @author Zhanghh
 * @date 2019-05-14
 */
package com.nebula.mooc.webserver.dao;

import com.nebula.mooc.core.entity.Course;
import com.nebula.mooc.core.entity.CourseChapter;
import com.nebula.mooc.core.entity.CourseComment;
import com.nebula.mooc.core.entity.CoursePage;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseDao {

    List<Course> getCourseList(CoursePage page);

    Course getCourse(CoursePage page);

    List<CourseChapter> getCourseChapterList(@Param("courseId") int courseId);

    List<CourseComment> getCourseCommentList(CoursePage page);
}
