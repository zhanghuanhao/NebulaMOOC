/*
 * @author Zhanghh
 * @date 2019/5/19
 */
package com.nebula.mooc.webserver.service.impl;

import com.nebula.mooc.core.Constant;
import com.nebula.mooc.core.entity.Course;
import com.nebula.mooc.core.entity.CourseChapter;
import com.nebula.mooc.core.entity.CourseSection;
import com.nebula.mooc.core.entity.CourseSectionComment;
import com.nebula.mooc.webserver.dao.CourseDao;
import com.nebula.mooc.webserver.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unchecked")
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
        if (course == null) return course;
        // 获取课程里的章列表
        List<CourseChapter> chapterList = courseDao.getCourseChapterList(courseId);
        if (chapterList == null) return course;
        course.setChapterList(chapterList);
        // 获取课程里的节列表
        List sectionList = new ArrayList(8);
        for (CourseChapter chapter : chapterList) {
            sectionList.add(courseDao.getCourseSectionList(chapter.getId()));
        }
        course.setSectionList(sectionList);
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

    public List getCourseSectionCommentList(long userId, long sectionId, int offset) {
        List<CourseSectionComment> commentList = courseDao.getCourseSectionCommentList(userId, sectionId, offset, Constant.PAGE_SIZE);
        if (commentList == null) return commentList;
        for (CourseSectionComment comment : commentList) {
            List commentReply = new ArrayList();
            commentReply.add(courseDao.getCourseSectionCommentReplyList(comment.getId()));
            comment.setReply(commentReply);
        }
        return commentList;
    }


}
