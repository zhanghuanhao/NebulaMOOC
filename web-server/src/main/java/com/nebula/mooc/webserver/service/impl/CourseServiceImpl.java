/*
 * @author Zhanghh
 * @date 2019/5/19
 */
package com.nebula.mooc.webserver.service.impl;

import com.nebula.mooc.core.Constant;
import com.nebula.mooc.core.entity.*;
import com.nebula.mooc.webserver.dao.CourseDao;
import com.nebula.mooc.webserver.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("CourseService")
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseDao courseDao;

    public int getCourseListTotal(String kindName) {
        return courseDao.getCourseTotal(kindName);
    }

    public List getCourseList(String kindName, int offset) {
        return courseDao.getCourseList(kindName, offset, Constant.PAGE_SIZE);
    }

    public Course getCourse(long userId, long courseId) {
        Course course = courseDao.getCourse(userId, courseId);
        if (course == null) return null;
        // 获取课程里的章列表
        List<CourseChapter> chapterList = courseDao.getCourseChapterList(courseId);
        if (chapterList == null) return course;
        course.setChapterList(chapterList);
        // 获取课程里的节列表
        for (CourseChapter chapter : chapterList) {
            List<CourseSection> sectionList = courseDao.getCourseSectionList(chapter.getId());
            chapter.setSectionList(sectionList);
        }
        return course;
    }

    public int getCourseCommentTotal(long courseId) {
        return courseDao.getCourseCommentTotal(courseId);
    }

    public List getCourseCommentList(long userId, long courseId, int offset) {
        return courseDao.getCourseCommentList(userId, courseId, offset, Constant.PAGE_SIZE);
    }

    public CourseSection getCourseSection(long sectionId) {
        return courseDao.getCourseSection(sectionId);
    }

    public int getCourseSectionCommentTotal(long sectionId) {
        return courseDao.getCourseSectionCommentTotal(sectionId);
    }

    public List<CourseSectionComment> getCourseSectionCommentList(long userId, long sectionId, int offset) {
        List<CourseSectionComment> commentList = courseDao.getCourseSectionCommentList(userId, sectionId, offset, Constant.PAGE_SIZE);
        if (commentList == null) return null;
        for (CourseSectionComment comment : commentList) {
            List<List<CourseSectionCommentReply>> commentReply = new ArrayList<>();
            commentReply.add(courseDao.getCourseSectionCommentReplyList(comment.getId()));
            comment.setReply(commentReply);
        }
        return commentList;
    }

    public boolean newCourse(Course course) {
        int result = courseDao.newCourse(course);
        if (result != 1) return false;
        long courseId = courseDao.getLastInsertId();
        for (CourseChapter chapter : course.getChapterList()) {
            chapter.setCourseId(courseId);
            result = courseDao.newCourseChapter(chapter);
            if (result != 1) return false;
            long chapterId = courseDao.getLastInsertId();
            List<CourseSection> courseSectionList = chapter.getSectionList();
            for (CourseSection section : courseSectionList) {
                section.setChapterId(chapterId);
                result = courseDao.newCourseSection(section);
                if (result != 1) return false;
            }
        }
        result = courseDao.increaseNum(course.getKindName());
        return result == 2;
    }

    public boolean updateCourse(Course course) {
        int result = courseDao.updateCourse(course);
        if (result != 1) return false;
        long courseId = course.getId();
        for (CourseChapter chapter : course.getChapterList()) {
            chapter.setCourseId(courseId);
            result = courseDao.updateCourseChapter(chapter);
            if (result != 1) return false;
            long chapterId = chapter.getId();
            List<CourseSection> courseSectionList = chapter.getSectionList();
            for (CourseSection section : courseSectionList) {
                section.setChapterId(chapterId);
                result = courseDao.updateCourseSection(section);
                if (result != 1) return false;
            }
        }
        return true;
    }
}
