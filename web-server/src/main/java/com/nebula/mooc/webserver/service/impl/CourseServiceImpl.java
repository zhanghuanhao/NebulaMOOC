/*
 * @author Zhanghh
 * @date 2019/5/19
 */
package com.nebula.mooc.webserver.service.impl;

import com.nebula.mooc.core.entity.*;
import com.nebula.mooc.webserver.dao.CourseDao;
import com.nebula.mooc.webserver.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("CourseService")
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseDao courseDao;

    public List<Course> getCourseList(CoursePage page) {
        return courseDao.getCourseList(page);
    }

    public Course getCourse(CoursePage page) {
        return courseDao.getCourse(page);
    }

    public List<CourseChapter> getCourseChapterList(CoursePage page) {
        return courseDao.getCourseChapterList(page);
    }

    public List<CourseComment> getCourseCommentList(CoursePage page) {
        return courseDao.getCourseCommentList(page);
    }

    public List<CourseSection> getCourseSectionList(CoursePage page) {
        return courseDao.getCourseSectionList(page);
    }

    public List<CourseSectionComment> getCourseSectionCommentList(CoursePage page) {
        return courseDao.getCourseSectionCommentList(page);
    }

    public List<CourseSectionCommentReply> getCourseSectionCommentReplyList(CoursePage page) {
        return courseDao.getCourseSectionCommentReplyList(page);
    }

}
