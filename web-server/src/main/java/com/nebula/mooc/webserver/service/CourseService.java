/*
 * @author Zhanghh
 * @date 2019/5/19
 */
package com.nebula.mooc.webserver.service;

import com.nebula.mooc.core.entity.*;

import java.util.List;

public interface CourseService {

    int getCourseListTotal(String kindName);

    List getCourseList(String kindName, int offset);

    List getHotCourseList();

    List getHomeCourseList();

    Course getCourse(long userId, long courseId);

    int getCourseCommentTotal(long courseId);

    List getCourseCommentList(long userId, long courseId, int offset);

    CourseSection getCourseSection(long sectionId);

    int getCourseSectionCommentTotal(long sectionId);

    List<CourseSectionComment> getCourseSectionCommentList(long userId, long sectionId, int offset);

    boolean newCourse(Course course);

    boolean ifStar(Course course);

    boolean courseStar(Course course);

    boolean delCourseStar(Course course);

    boolean ifLike(Course course);

    boolean courseLike(Course course);

    boolean delCourseLike(Course course);

    boolean courseComment(CourseComment courseComment);

    boolean delCourseComment(CourseComment courseComment);

    boolean ifCourseCommentStar(CourseComment courseComment);

    boolean courseCommentStar(CourseComment courseComment);

    boolean delCourseCommentStar(CourseComment courseComment);

    boolean sectionComment(CourseSectionComment courseSectionComment);

    boolean delSectionComment(CourseSectionComment courseSectionComment);

    boolean ifSectionCommentStar(CourseSectionComment courseSectionComment);

    boolean sectionCommentStar(CourseSectionComment courseSectionComment);

    boolean delSectionCommentStar(CourseSectionComment courseSectionComment);

    boolean sectionCommentReply(CourseSectionCommentReply courseSectionCommentReply);

    boolean delSectionCommentReply(CourseSectionCommentReply courseSectionCommentReply);

    long lastReplyId();


}
