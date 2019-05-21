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

    int updateCourse(Course course);

    int updateCourseChapter(CourseChapter courseChapter);

    int updateCourseSection(CourseSection courseSection);
}
