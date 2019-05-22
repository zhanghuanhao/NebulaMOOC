/*
 * @author Zhanghh
 * @date 2019/5/19
 */
package com.nebula.mooc.webserver.controller;

import com.nebula.mooc.core.Constant;
import com.nebula.mooc.core.entity.*;
import com.nebula.mooc.webserver.service.CourseService;
import com.nebula.mooc.webserver.service.FileService;
import com.nebula.mooc.webserver.util.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@SuppressWarnings("unchecked")
@RestController
@RequestMapping("/api/course/")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @Autowired
    private FileService fileService;

    @GetMapping(value = "getHomeCourseList")
    public Return getCourseList() {
        return new Return(courseService.getHomeCourseList());
    }

    @PostMapping(value = "getCourseList")
    public Return getCourseList(int pageIndex, int kind) {
        if (pageIndex <= 0 || kind < 0 || kind > 10) return new Return(Constant.CLIENT_ERROR_CODE, "参数错误！");
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
    public Return newCourse(HttpServletRequest request, Course course,
                            int kind, @RequestParam(required = false) MultipartFile file) {
        if (kind < 0 || kind > 10) return new Return(Constant.CLIENT_ERROR_CODE, "参数错误！");
        UserInfo userInfo = (UserInfo) request.getSession().getAttribute(Constant.USERINFO);
        if (file == null || file.isEmpty())
            course.setCourseHeadUrl("default");
        else {
            if (file.getContentType() == null || !file.getContentType().startsWith("image") || !FileUtil.isImg(file))
                return new Return(Constant.CLIENT_FILE_ERROR, "图片格式错误！");
            if (!fileService.uploadHead(userInfo, file))
                return new Return(Constant.CLIENT_FILE_ERROR, "图片上传失败！");
        }
        String kindName = Constant.KIND_MAP.get(kind);
        course.setUserId(userInfo.getId());
        course.setKindName(kindName);
        if (courseService.newCourse(course)) return Return.SUCCESS;
        else return new Return(Constant.CLIENT_ERROR_CODE, "创建新课程失败，请重试！");
    }

    @PostMapping(value = "updateCourse")
    public Return updateCourse(HttpServletRequest request, Course course) {
        UserInfo userInfo = (UserInfo) request.getSession().getAttribute(Constant.USERINFO);
        course.setUserId(userInfo.getId());
        if (courseService.updateCourse(course)) return Return.SUCCESS;
        else return new Return(Constant.CLIENT_ERROR_CODE, "修改课程失败，请重试！");
    }

    @PostMapping(value = "courseStar")
    public Return courseStar(HttpServletRequest request, Course course) {
        UserInfo userInfo = (UserInfo) request.getSession().getAttribute(Constant.USERINFO);
        course.setUserId(userInfo.getId());
        if (courseService.ifStar(course))
            return new Return(105, "您已点赞！");
        if (courseService.courseStar(course))
            return Return.SUCCESS;
        return Return.SERVER_ERROR;
    }

    @PostMapping(value = "delCourseStar")
    public Return delCourseStar(HttpServletRequest request, Course course) {
        UserInfo userInfo = (UserInfo) request.getSession().getAttribute(Constant.USERINFO);
        course.setUserId(userInfo.getId());
        if (!courseService.ifStar(course))
            return new Return(106, "您未点赞！");
        if (courseService.delCourseStar(course))
            return Return.SUCCESS;
        return Return.SERVER_ERROR;
    }


    @PostMapping(value = "courseLike")
    public Return courseLike(HttpServletRequest request, Course course) {
        UserInfo userInfo = (UserInfo) request.getSession().getAttribute(Constant.USERINFO);
        course.setUserId(userInfo.getId());
        if (courseService.ifLike(course))
            return new Return(105, "您已收藏！");
        if (courseService.courseLike(course))
            return Return.SUCCESS;
        return Return.SERVER_ERROR;
    }

    @PostMapping(value = "delCourseLike")
    public Return delCourseLike(HttpServletRequest request, Course course) {
        UserInfo userInfo = (UserInfo) request.getSession().getAttribute(Constant.USERINFO);
        course.setUserId(userInfo.getId());
        if (!courseService.ifLike(course))
            return new Return(106, "您未收藏！");
        if (courseService.delCourseLike(course))
            return Return.SUCCESS;
        return Return.SERVER_ERROR;
    }

    @PostMapping(value = "courseComment")
    public Return courseComment(HttpServletRequest request, CourseComment courseComment) {
        UserInfo userInfo = (UserInfo) request.getSession().getAttribute(Constant.USERINFO);
        courseComment.setUserId(userInfo.getId());
        if (courseService.courseComment(courseComment))
            return Return.SUCCESS;
        return Return.SERVER_ERROR;
    }

    @PostMapping(value = "delCourseComment")
    public Return delCourseComment(HttpServletRequest request, CourseComment courseComment) {
        UserInfo userInfo = (UserInfo) request.getSession().getAttribute(Constant.USERINFO);
        courseComment.setUserId(userInfo.getId());
        if (courseService.delCourseComment(courseComment))
            return Return.SUCCESS;
        return Return.SERVER_ERROR;
    }

    @PostMapping(value = "courseCommentStar")
    public Return courseCommentStar(HttpServletRequest request, CourseComment courseComment) {
        UserInfo userInfo = (UserInfo) request.getSession().getAttribute(Constant.USERINFO);
        courseComment.setUserId(userInfo.getId());
        if (courseService.ifCourseCommentStar(courseComment))
            return new Return(105, "您已点赞！");
        if (courseService.courseCommentStar(courseComment))
            return Return.SUCCESS;
        return Return.SERVER_ERROR;
    }

    @PostMapping(value = "delCourseCommentStar")
    public Return delCourseCommentStar(HttpServletRequest request, CourseComment courseComment) {
        UserInfo userInfo = (UserInfo) request.getSession().getAttribute(Constant.USERINFO);
        courseComment.setUserId(userInfo.getId());
        if (!courseService.ifCourseCommentStar(courseComment))
            return new Return(106, "您未点赞！");
        if (courseService.delCourseCommentStar(courseComment))
            return Return.SUCCESS;
        return Return.SERVER_ERROR;
    }

    @PostMapping(value = "sectionComment")
    public Return sectionComment(HttpServletRequest request, CourseSectionComment courseSectionComment) {
        UserInfo userInfo = (UserInfo) request.getSession().getAttribute(Constant.USERINFO);
        courseSectionComment.setUserId(userInfo.getId());
        if (courseService.sectionComment(courseSectionComment))
            return Return.SUCCESS;
        return Return.SERVER_ERROR;
    }

    @PostMapping(value = "delSectionComment")
    public Return delSectionComment(HttpServletRequest request, CourseSectionComment courseSectionComment) {
        UserInfo userInfo = (UserInfo) request.getSession().getAttribute(Constant.USERINFO);
        courseSectionComment.setUserId(userInfo.getId());
        if (courseService.delSectionComment(courseSectionComment))
            return Return.SUCCESS;
        return Return.SERVER_ERROR;
    }


    @PostMapping(value = "sectionCommentStar")
    public Return sectionCommentStar(HttpServletRequest request, CourseSectionComment courseSectionComment) {
        UserInfo userInfo = (UserInfo) request.getSession().getAttribute(Constant.USERINFO);
        courseSectionComment.setUserId(userInfo.getId());
        if (courseService.ifSectionCommentStar(courseSectionComment))
            return new Return(105, "您已点赞！");
        if (courseService.sectionCommentStar(courseSectionComment))
            return Return.SUCCESS;
        return Return.SERVER_ERROR;
    }

    @PostMapping(value = "delSectionCommentStar")
    public Return delSectionCommentStar(HttpServletRequest request, CourseSectionComment courseSectionComment) {
        UserInfo userInfo = (UserInfo) request.getSession().getAttribute(Constant.USERINFO);
        courseSectionComment.setUserId(userInfo.getId());
        if (!courseService.ifSectionCommentStar(courseSectionComment))
            return new Return(106, "您未点赞");
        if (courseService.delSectionCommentStar(courseSectionComment))
            return Return.SUCCESS;
        return Return.SERVER_ERROR;
    }

    @PostMapping(value = "sectionCommentReply")
    public Return sectionCommentReply(HttpServletRequest request, CourseSectionCommentReply courseSectionCommentReply) {
        UserInfo userInfo = (UserInfo) request.getSession().getAttribute(Constant.USERINFO);
        courseSectionCommentReply.setFromId(userInfo.getId());
        if (courseService.sectionCommentReply(courseSectionCommentReply))
            return new Return(100, "" + courseService.lastReplyId());
        return Return.SERVER_ERROR;
    }

    @PostMapping(value = "delSectionCommentReply")
    public Return delSectionCommentReply(HttpServletRequest request, CourseSectionCommentReply courseSectionCommentReply) {
        UserInfo userInfo = (UserInfo) request.getSession().getAttribute(Constant.USERINFO);
        courseSectionCommentReply.setFromId(userInfo.getId());
        if (courseService.delSectionCommentReply(courseSectionCommentReply))
            return Return.SUCCESS;
        return Return.SERVER_ERROR;
    }


}
