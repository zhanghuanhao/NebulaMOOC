/*
 * @author Zhanghh
 * @date 2019/5/19
 */
package com.nebula.mooc.webserver.controller;

import com.nebula.mooc.core.Constant;
import com.nebula.mooc.core.entity.Course;
import com.nebula.mooc.core.entity.Return;
import com.nebula.mooc.core.entity.UserInfo;
import com.nebula.mooc.webserver.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@SuppressWarnings("unchecked")
@RestController
@RequestMapping("/api/course/")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @PostMapping(value = "getCourseList")
    public Return getCourseList(int pageIndex, int kind) {
        if (pageIndex <= 0 || kind < 0 || kind > 10) return new Return(Constant.CLIENT_ERROR_CODE, "参数错误！");
        String kindName = Constant.KIND_MAP.get(kind);      // 获取类型名
        System.out.println(kindName);
        int total = courseService.getCourseListTotal(kindName);     // 总数
        int offset = (pageIndex - 1) * Constant.PAGE_SIZE;  // 偏移量
        Return ret = new Return<List>();
        // 如果总数为0或者偏移量过大
        if (total == 0 || offset > total) {
            // 设置总数为0，放在Return的Msg中
            ret.setMsg(String.valueOf(0));
            return ret;
        }
        ret.setMsg(String.valueOf(total));
        ret.setData(courseService.getCourseList(kindName, offset));
        return ret;
    }

    @PostMapping(value = "getCourse")
    public Return getCourse(HttpServletRequest request, long courseId) {
        if (courseId <= 0) return new Return(Constant.CLIENT_ERROR_CODE, "参数错误！");
        UserInfo userInfo = (UserInfo) request.getSession().getAttribute(Constant.USERINFO);
        return new Return(courseService.getCourse(userInfo.getId(), courseId));
    }

    @PostMapping(value = "getCourseCommentList")
    public Return getCourseCommentList(HttpServletRequest request, long courseId, int pageIndex) {
        if (courseId <= 0 || pageIndex <= 0) return new Return(Constant.CLIENT_ERROR_CODE, "参数错误！");
        UserInfo userInfo = (UserInfo) request.getSession().getAttribute(Constant.USERINFO);
        int total = courseService.getCourseCommentTotal(courseId);     // 总数
        int offset = (pageIndex - 1) * Constant.PAGE_SIZE;  // 偏移量
        Return ret = new Return<List>();
        // 如果总数为0或者偏移量过大
        if (total == 0 || offset > total) {
            // 设置总数为0，放在Return的Msg中
            ret.setMsg(String.valueOf(0));
            return ret;
        }
        ret.setMsg(String.valueOf(total));
        ret.setData(courseService.getCourseCommentList(userInfo.getId(), courseId, offset));
        return ret;
    }

    @PostMapping(value = "getCourseSection")
    public Return getCourseSection(long sectionId) {
        if (sectionId <= 0) return new Return(Constant.CLIENT_ERROR_CODE, "参数错误！");
        return new Return(courseService.getCourseSection(sectionId));
    }

    @PostMapping(value = "getCourseSectionCommentList")
    public Return getCourseSectionCommentList(HttpServletRequest request, long sectionId, int pageIndex) {
        if (sectionId <= 0 || pageIndex <= 0) return new Return(Constant.CLIENT_ERROR_CODE, "参数错误！");
        UserInfo userInfo = (UserInfo) request.getSession().getAttribute(Constant.USERINFO);
        int total = courseService.getCourseSectionCommentTotal(sectionId);     // 总数
        int offset = (pageIndex - 1) * Constant.PAGE_SIZE;  // 偏移量
        Return ret = new Return<List>();
        // 如果总数为0或者偏移量过大
        if (total == 0 || offset > total) {
            // 设置总数为0，放在Return的Msg中
            ret.setMsg(String.valueOf(0));
            return ret;
        }
        ret.setMsg(String.valueOf(total));
        ret.setData(courseService.getCourseSectionCommentList(userInfo.getId(), sectionId, offset));
        return ret;
    }

    @PostMapping(value = "newCourse")
    public Return newCourse(HttpServletRequest request, Course course) {
        if (course == null) return new Return(Constant.CLIENT_ERROR_CODE, "参数错误！");
        UserInfo userInfo = (UserInfo) request.getSession().getAttribute(Constant.USERINFO);
        course.setUserId(userInfo.getId());
        if (courseService.newCourse(course)) return Return.SUCCESS;
        else return new Return(Constant.CLIENT_ERROR_CODE, "创建新课程失败，请重试！");
    }

    @PostMapping(value = "updateCourse")
    public Return updateCourse(HttpServletRequest request, Course course) {
        if (course == null) return new Return(Constant.CLIENT_ERROR_CODE, "参数错误！");
        UserInfo userInfo = (UserInfo) request.getSession().getAttribute(Constant.USERINFO);
        course.setUserId(userInfo.getId());
        if (courseService.updateCourse(course)) return Return.SUCCESS;
        else return new Return(Constant.CLIENT_ERROR_CODE, "修改课程失败，请重试！");
    }
}
