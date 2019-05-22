/*
 * @author Zhanghh
 * @date 2019/5/7
 */
package com.nebula.mooc.webserver.controller;

import com.nebula.mooc.core.Constant;
import com.nebula.mooc.core.entity.Return;
import com.nebula.mooc.core.entity.UserInfo;
import com.nebula.mooc.webserver.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/video/")
public class VideoController {

    @Autowired
    private VideoService videoService;

    @GetMapping("getVideoList")
    public Return getVideoList(HttpServletRequest request) {
        UserInfo userInfo = (UserInfo) request.getSession().getAttribute(Constant.USERINFO);
        return new Return<>(videoService.getVideoList(userInfo.getId()));
    }

    @PostMapping("uploadVideo")
    public Return uploadVideo(HttpServletRequest request, MultipartFile file) {
        if (file.isEmpty())
            return new Return(Constant.CLIENT_FILE_ERROR, "文件不能为空！");
        else if (file.getContentType() == null || !file.getContentType().startsWith("video"))
            return new Return(Constant.CLIENT_FILE_ERROR, "文件格式错误！");
        UserInfo userInfo = (UserInfo) request.getSession().getAttribute(Constant.USERINFO);
        if (videoService.uploadVideo(userInfo, file))
            return Return.SUCCESS;
        else
            return new Return(Constant.CLIENT_FILE_ERROR, "上传失败！");
    }

}
