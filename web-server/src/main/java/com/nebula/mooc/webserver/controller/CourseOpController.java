/*
 * @author Zhanghh
 * @date 2019/5/19
 */
package com.nebula.mooc.webserver.controller;

import com.nebula.mooc.core.Constant;
import com.nebula.mooc.core.entity.*;
import com.nebula.mooc.webserver.service.CourseService;
import com.nebula.mooc.webserver.service.FileService;
import com.nebula.mooc.webserver.service.ScoreService;
import com.nebula.mooc.webserver.util.CacheUtil;
import com.nebula.mooc.webserver.util.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Caching;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

@SuppressWarnings("unchecked")
@RestController
@RequestMapping("/api/course/")
public class CourseOpController {

    @Autowired
    private CourseService courseService;

    @Autowired
    private FileService fileService;

    @Autowired
    private ScoreService scoreService;

    @Caching(evict = {@CacheEvict(value = "getCourseList", keyGenerator = "kindMapKeyGenerator"),
            @CacheEvict(value = "getCourseList", key = "'TOTAL'")})
    @PostMapping(value = "newCourse")
    public Return newCourse(int kind, HttpServletRequest request,
                            Course course, @RequestParam(required = false) MultipartFile file) {
        if (kind < 0 || kind > 10) return Return.CLIENT_PARAM_ERROR;
        UserInfo userInfo = CacheUtil.getUserInfo(request);
        if (file == null || file.isEmpty())
            course.setCourseHeadUrl("default");
        else {
            String fileExtName;
            if ((fileExtName = FileUtil.isImg(file)) == null)
                return new Return(Constant.CLIENT_FILE_ERROR, "图片格式错误！");
            String fileName = fileService.uploadHead(file, fileExtName);
            if (fileName == null)
                return new Return(Constant.CLIENT_FILE_ERROR, "图片上传失败！");
            course.setCourseHeadUrl(fileName);
        }
        String kindName = Constant.KIND_MAP.get(kind);
        course.setUserId(userInfo.getId());
        course.setKindName(kindName);
        if (courseService.newCourse(course)) return Return.SUCCESS;
        else return new Return(Constant.CLIENT_ERROR_CODE, "创建新课程失败，请重试！");
    }

    @Caching(evict = {@CacheEvict(value = "getCourseList", key = "#course.kindName", condition = "#course.kindName != null"),
            @CacheEvict(value = "getCourseList", key = "'TOTAL'")})
    @PostMapping(value = "courseStar")
    public Return courseStar(HttpServletRequest request, Course course) {
        UserInfo userInfo = CacheUtil.getUserInfo(request);
        course.setUserId(userInfo.getId());
        if (courseService.ifStar(course))
            return Return.STAR_LIKE_ALREADY;
        if (courseService.courseStar(course)) {
            scoreService.incrCourse(userInfo.getId(), course.getId(), Constant.STAR_SCORE);
            return Return.SUCCESS;
        }
        return Return.SERVER_ERROR;
    }

    @Caching(evict = {@CacheEvict(value = "getCourseList", key = "#course.kindName", condition = "#course.kindName != null"),
            @CacheEvict(value = "getCourseList", key = "'TOTAL'")})
    @PostMapping(value = "delCourseStar")
    public Return delCourseStar(HttpServletRequest request, Course course) {
        UserInfo userInfo = CacheUtil.getUserInfo(request);
        course.setUserId(userInfo.getId());
        if (!courseService.ifStar(course))
            return Return.UN_STAR_LIKE;
        if (courseService.delCourseStar(course)) {
            scoreService.decrCourse(userInfo.getId(), course.getId(), Constant.STAR_SCORE);
            return Return.SUCCESS;
        }
        return Return.SERVER_ERROR;
    }

    @Caching(evict = {@CacheEvict(value = "getCourseList", key = "#course.kindName", condition = "#course.kindName != null"),
            @CacheEvict(value = "getCourseList", key = "'TOTAL'")})
    @PostMapping(value = "courseLike")
    public Return courseLike(HttpServletRequest request, Course course) {
        UserInfo userInfo = CacheUtil.getUserInfo(request);
        course.setUserId(userInfo.getId());
        if (courseService.ifLike(course))
            return Return.STAR_LIKE_ALREADY;
        if (courseService.courseLike(course)) {
            scoreService.incrCourse(userInfo.getId(), course.getId(), Constant.LIKE_SCORE);
            return Return.SUCCESS;
        }
        return Return.SERVER_ERROR;
    }

    @Caching(evict = {@CacheEvict(value = "getCourseList", key = "#course.kindName", condition = "#course.kindName != null"),
            @CacheEvict(value = "getCourseList", key = "'TOTAL'")})
    @PostMapping(value = "delCourseLike")
    public Return delCourseLike(HttpServletRequest request, Course course) {
        UserInfo userInfo = CacheUtil.getUserInfo(request);
        course.setUserId(userInfo.getId());
        if (!courseService.ifLike(course))
            return Return.UN_STAR_LIKE;
        if (courseService.delCourseLike(course)) {
            scoreService.decrCourse(userInfo.getId(), course.getId(), Constant.LIKE_SCORE);
            return Return.SUCCESS;
        }
        return Return.SERVER_ERROR;
    }

    @PostMapping(value = "courseComment")
    public Return courseComment(HttpServletRequest request, CourseComment courseComment) {
        UserInfo userInfo = CacheUtil.getUserInfo(request);
        courseComment.setUserId(userInfo.getId());
        if (courseService.courseComment(courseComment))
            return Return.SUCCESS;
        return Return.SERVER_ERROR;
    }

    @PostMapping(value = "delCourseComment")
    public Return delCourseComment(HttpServletRequest request, CourseComment courseComment) {
        UserInfo userInfo = CacheUtil.getUserInfo(request);
        courseComment.setUserId(userInfo.getId());
        if (courseService.delCourseComment(courseComment))
            return Return.SUCCESS;
        return Return.SERVER_ERROR;
    }

    @PostMapping(value = "courseCommentStar")
    public Return courseCommentStar(HttpServletRequest request, CourseComment courseComment) {
        UserInfo userInfo = CacheUtil.getUserInfo(request);
        courseComment.setUserId(userInfo.getId());
        if (courseService.ifCourseCommentStar(courseComment))
            return Return.STAR_LIKE_ALREADY;
        if (courseService.courseCommentStar(courseComment))
            return Return.SUCCESS;
        return Return.SERVER_ERROR;
    }

    @PostMapping(value = "delCourseCommentStar")
    public Return delCourseCommentStar(HttpServletRequest request, CourseComment courseComment) {
        UserInfo userInfo = CacheUtil.getUserInfo(request);
        courseComment.setUserId(userInfo.getId());
        if (!courseService.ifCourseCommentStar(courseComment))
            return Return.UN_STAR_LIKE;
        if (courseService.delCourseCommentStar(courseComment))
            return Return.SUCCESS;
        return Return.SERVER_ERROR;
    }

    @PostMapping(value = "sectionComment")
    public Return sectionComment(HttpServletRequest request, CourseSectionComment courseSectionComment) {
        UserInfo userInfo = CacheUtil.getUserInfo(request);
        courseSectionComment.setUserId(userInfo.getId());
        if (courseService.sectionComment(courseSectionComment))
            return Return.SUCCESS;
        return Return.SERVER_ERROR;
    }

    @PostMapping(value = "delSectionComment")
    public Return delSectionComment(HttpServletRequest request, CourseSectionComment courseSectionComment) {
        UserInfo userInfo = CacheUtil.getUserInfo(request);
        courseSectionComment.setUserId(userInfo.getId());
        if (courseService.delSectionComment(courseSectionComment))
            return Return.SUCCESS;
        return Return.SERVER_ERROR;
    }


    @PostMapping(value = "sectionCommentStar")
    public Return sectionCommentStar(HttpServletRequest request, CourseSectionComment courseSectionComment) {
        UserInfo userInfo = CacheUtil.getUserInfo(request);
        courseSectionComment.setUserId(userInfo.getId());
        if (courseService.ifSectionCommentStar(courseSectionComment))
            return Return.STAR_LIKE_ALREADY;
        if (courseService.sectionCommentStar(courseSectionComment))
            return Return.SUCCESS;
        return Return.SERVER_ERROR;
    }

    @PostMapping(value = "delSectionCommentStar")
    public Return delSectionCommentStar(HttpServletRequest request, CourseSectionComment courseSectionComment) {
        UserInfo userInfo = CacheUtil.getUserInfo(request);
        courseSectionComment.setUserId(userInfo.getId());
        if (!courseService.ifSectionCommentStar(courseSectionComment))
            return Return.UN_STAR_LIKE;
        if (courseService.delSectionCommentStar(courseSectionComment))
            return Return.SUCCESS;
        return Return.SERVER_ERROR;
    }

    @PostMapping(value = "sectionCommentReply")
    public Return sectionCommentReply(HttpServletRequest request, CourseSectionCommentReply courseSectionCommentReply) {
        UserInfo userInfo = CacheUtil.getUserInfo(request);
        courseSectionCommentReply.setFromId(userInfo.getId());
        if (courseService.sectionCommentReply(courseSectionCommentReply))
            return new Return(100, "" + courseService.lastReplyId());
        return Return.SERVER_ERROR;
    }

    @PostMapping(value = "delSectionCommentReply")
    public Return delSectionCommentReply(HttpServletRequest request, CourseSectionCommentReply courseSectionCommentReply) {
        UserInfo userInfo = CacheUtil.getUserInfo(request);
        courseSectionCommentReply.setFromId(userInfo.getId());
        if (courseService.delSectionCommentReply(courseSectionCommentReply))
            return Return.SUCCESS;
        return Return.SERVER_ERROR;
    }


}
