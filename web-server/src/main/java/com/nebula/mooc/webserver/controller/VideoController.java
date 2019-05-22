/*
 * @author Zhanghh
 * @date 2019/5/7
 */
package com.nebula.mooc.webserver.controller;

import com.nebula.mooc.core.Constant;
import com.nebula.mooc.core.entity.Return;
import com.nebula.mooc.core.entity.UserInfo;
import com.nebula.mooc.webserver.service.FileService;
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
    private FileService fileService;

    @GetMapping("getVideoList")
    public Return getVideoList(HttpServletRequest request) {
        UserInfo userInfo = (UserInfo) request.getSession().getAttribute(Constant.USERINFO);
        return new Return<>(fileService.getVideoList(userInfo.getId()));
    }

    @PostMapping("uploadVideo")
    public Return uploadVideo(HttpServletRequest request, MultipartFile file) {
        if (file == null || file.isEmpty())
            return new Return(Constant.CLIENT_FILE_ERROR, "视频不能为空！");
        UserInfo userInfo = (UserInfo) request.getSession().getAttribute(Constant.USERINFO);
        if (fileService.uploadVideo(userInfo, file))
            return Return.SUCCESS;
        else
            return new Return(Constant.CLIENT_FILE_ERROR, "视频上传失败！");
    }

}
