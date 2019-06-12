/*
 * @author Zhanghh
 * @date 2019/5/19
 */
package com.nebula.mooc.webserver.controller;

import com.nebula.mooc.core.Constant;
import com.nebula.mooc.core.entity.Return;
import com.nebula.mooc.core.entity.UserInfo;
import com.nebula.mooc.webserver.service.CourseService;
import com.nebula.mooc.webserver.service.ScoreService;
import com.nebula.mooc.webserver.util.CacheUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@SuppressWarnings("unchecked")
@RestController
@RequestMapping("/sys/course/")
public class CourseQueryController {

    @Autowired
    private CourseService courseService;

    @Autowired
    private ScoreService scoreService;

    private long getUserId(HttpServletRequest request) {
        UserInfo userInfo = CacheUtil.getUserInfo(request);
        if (userInfo != null) return userInfo.getId();
        else return 0;
    }

    @Cacheable(value = "getRecommendCourseList", keyGenerator = "userIdKeyGenerator")
    @GetMapping(value = "getRecommendCourseList")
    public Return getRecommendCourseList(HttpServletRequest request) {
        long userId = getUserId(request);
        if (userId == 0) return new Return(Constant.CLIENT_NOT_LOGIN);
        return new Return(courseService.getRecommendCourseList(userId));
    }

    @Cacheable(value = "HOME", key = "'getHomeCourseList'")
    @GetMapping(value = "getHomeCourseList")
    public Return getHomeCourseList() {
        return new Return(courseService.getHomeCourseList());
    }

    @Cacheable(value = "HOT", key = "'getHotCourseList'")
    @GetMapping(value = "getHotCourseList")
    public Return getHotCourseList() {
        return new Return(courseService.getHotCourseList());
    }

    @Cacheable(value = "getCourseList", keyGenerator = "kindMapKeyGenerator", condition = "#pageIndex == 1")
    @PostMapping(value = "getCourseList")
    public Return getCourseList(int kind, int pageIndex) {
        if (pageIndex <= 0 || kind < 0 || kind > 10) return Return.CLIENT_PARAM_ERROR;
        String kindName = Constant.KIND_MAP.get(kind);      // 获取类型名
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


    @PostMapping(value = "getLikeCourse")
    public Return getLikeCourse(HttpServletRequest request, int pageIndex) {
        if (pageIndex <= 0) return Return.CLIENT_PARAM_ERROR;
        long userId = getUserId(request);
        int total = courseService.likeCourseTotal(userId);     // 总数
        int offset = (pageIndex - 1) * Constant.PAGE_SIZE;  // 偏移量
        Return ret = new Return<List>();
        // 如果总数为0或者偏移量过大
        if (total == 0 || offset > total) {
            // 设置总数为0，放在Return的Msg中
            ret.setMsg(String.valueOf(0));
            return ret;
        }
        ret.setMsg(String.valueOf(total));
        ret.setData(courseService.getLikeCourse(userId, offset));
        return ret;
    }

    @PostMapping(value = "getCourse")
    public Return getCourse(HttpServletRequest request, long courseId) {
        if (courseId <= 0) return Return.CLIENT_PARAM_ERROR;
        long userId = getUserId(request);
        if (userId != 0)
            scoreService.viewCourse(userId, courseId, Constant.VIEW_SCORE);
        return new Return(courseService.getCourse(userId, courseId));
    }

    @PostMapping(value = "getCourseCommentList")
    public Return getCourseCommentList(HttpServletRequest request, long courseId, int pageIndex) {
        if (courseId <= 0 || pageIndex <= 0) return Return.CLIENT_PARAM_ERROR;
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
        ret.setData(courseService.getCourseCommentList(getUserId(request), courseId, offset));
        return ret;
    }

    @PostMapping(value = "getCourseSection")
    public Return getCourseSection(long sectionId) {
        if (sectionId <= 0) return Return.CLIENT_PARAM_ERROR;
        return new Return(courseService.getCourseSection(sectionId));
    }

    @PostMapping(value = "getCourseSectionCommentList")
    public Return getCourseSectionCommentList(HttpServletRequest request, long sectionId, int pageIndex) {
        if (sectionId <= 0 || pageIndex <= 0) return Return.CLIENT_PARAM_ERROR;
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
        ret.setData(courseService.getCourseSectionCommentList(getUserId(request), sectionId, offset));
        return ret;
    }
}
