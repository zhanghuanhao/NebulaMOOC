/*
 * @author Zhanghh
 * @date 2019-05-14
 */
package com.nebula.mooc.webserver.dao;

import com.nebula.mooc.core.entity.*;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseDao {

    int getCourseTotal(@Param("kindName") String kindName);

    List<Course> getCourseList(@Param("kindName") String kindName,
                               @Param("offset") int offset,
                               @Param("pageSize") int pageSize);

    List<Course> getLikeCourse(@Param("userId") long userId,
                               @Param("offset") int offset,
                               @Param("pageSize") int pageSize);

    int likeCourseTotal(@Param("userId") long userId);

    List<Course> getHotCourseList(@Param("pageSize") int pageSize);

    Course getCourse(@Param("userId") long userId,
                     @Param("courseId") long courseId);

    List<CourseChapter> getCourseChapterList(@Param("courseId") long courseId);

    List<CourseSection> getCourseSectionList(@Param("chapterId") long chapterId);

    int getCourseCommentTotal(@Param("courseId") long courseId);

    List<CourseComment> getCourseCommentList(@Param("userId") long userId,
                                             @Param("courseId") long courseId,
                                             @Param("offset") int offset,
                                             @Param("pageSize") int pageSize);

    CourseSection getCourseSection(@Param("sectionId") long sectionId);

    int getCourseSectionCommentTotal(@Param("sectionId") long sectionId);

    List<CourseSectionComment> getCourseSectionCommentList(@Param("userId") long userId,
                                                           @Param("sectionId") long sectionId,
                                                           @Param("offset") int offset,
                                                           @Param("pageSize") int pageSize);

    List<CourseSectionCommentReply> getCourseSectionCommentReplyList(@Param("commentId") long commentId);

    long getLastInsertId();

    int newCourse(Course course);

    int newCourseChapter(CourseChapter courseChapter);

    int newCourseSection(CourseSection courseSection);

    int increaseNum(@Param("kindName") String kindName);

    int courseStar(@Param("userId") long userId, @Param("courseId") long courseId);

    int delCourseStar(@Param("userId") long userId, @Param("courseId") long courseId);

    int addCourseStar(@Param("courseId") long courseId);

    int subCourseStar(@Param("courseId") long courseId);

    int ifStar(@Param("userId") long userId, @Param("courseId") long courseId);

    int courseLike(@Param("userId") long userId, @Param("courseId") long courseId);

    int delCourseLike(@Param("userId") long userId, @Param("courseId") long courseId);

    int addCourseLike(@Param("courseId") long courseId);

    int subCourseLike(@Param("courseId") long courseId);

    int ifLike(@Param("userId") long userId, @Param("courseId") long courseId);

    int courseComment(CourseComment courseComment);

    int delCourseComment(@Param("userId") long userId, @Param("id") long id);

    int courseCommentStar(@Param("userId") long userId, @Param("commentId") long commentId);

    int delCourseCommentStar(@Param("userId") long userId, @Param("commentId") long commentId);

    int addCourseCommentStar(@Param("commentId") long commentId);

    int subCourseCommentStar(@Param("commentId") long commentId);

    int ifCourseCommentStar(@Param("userId") long userId, @Param("commentId") long commentId);

    int sectionComment(CourseSectionComment courseSectionComment);

    int delSectionComment(@Param("userId") long userId, @Param("id") long id);

    int sectionCommentStar(@Param("userId") long userId, @Param("commentId") long commentId);

    int delSectionCommentStar(@Param("userId") long userId, @Param("commentId") long commentId);

    int addSectionCommentStar(@Param("commentId") long commentId);

    int subSectionCommentStar(@Param("commentId") long commentId);

    int ifSectionCommentStar(@Param("userId") long userId, @Param("commentId") long commentId);

    int sectionCommentReply(CourseSectionCommentReply courseSectionCommentReply);

    int delSectionCommentReply(@Param("id") long id, @Param("fromId") long fromId);

    int lastReplyId();//新建回复后调用来返回回复的id





}
