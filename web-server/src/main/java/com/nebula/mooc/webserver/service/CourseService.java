/*
 * @author Zhanghh
 * @date 2019/5/19
 */
package com.nebula.mooc.webserver.service;

import com.nebula.mooc.core.entity.Course;
import com.nebula.mooc.core.entity.CourseSection;
import com.nebula.mooc.core.entity.CourseSectionComment;

import java.util.List;

public interface CourseService {

    int getCourseListTotal(String kindName);

    List getCourseList(String kindName, int offset);

    Course getCourse(long userId, long courseId);

    int getCourseCommentTotal(long courseId);

    List getCourseCommentList(long userId, long courseId, int offset);

    CourseSection getCourseSection(long sectionId);

    int getCourseSectionCommentTotal(long sectionId);

    List<CourseSectionComment> getCourseSectionCommentList(long userId, long sectionId, int offset);

    boolean newCourse(Course course);

    boolean updateCourse(Course course);

}
