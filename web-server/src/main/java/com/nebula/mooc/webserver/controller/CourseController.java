/*
 * @author Zhanghh
 * @date 2019/5/19
 */
package com.nebula.mooc.webserver.controller;

import com.nebula.mooc.core.entity.CoursePage;
import com.nebula.mooc.webserver.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/course/")
public class CourseController {

    @Autowired
    private CourseService courseService;


    @PostMapping(value = "test")
    public List checkUser(CoursePage page) {
//        CoursePage page = new CoursePage();
//        page.setUserId(1);
//        page.setCourseId(1);
        return courseDao.getCourseSectionCommentReplyList(page);
    }
}
