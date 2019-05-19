/*
 * @author Zhanghh
 * @date 2019/5/19
 */
package com.nebula.mooc.webserver.service;

import com.nebula.mooc.core.entity.*;

import java.util.List;

public interface CourseService {

    List<Course> getCourseList(CoursePage page);

    Course getCourse(CoursePage page);

    List<CourseChapter> getCourseChapterList(CoursePage page);

    List<CourseComment> getCourseCommentList(CoursePage page);

    List<CourseSection> getCourseSectionList(CoursePage page);

    List<CourseSectionComment> getCourseSectionCommentList(CoursePage page);

    List<CourseSectionCommentReply> getCourseSectionCommentReplyList(CoursePage page);

}
