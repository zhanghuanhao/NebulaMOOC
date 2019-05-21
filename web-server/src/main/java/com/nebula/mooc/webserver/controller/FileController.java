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
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/sys/file/")
public class FileController {

    @Autowired
    private FileService fileService;

    private static final String image = "image";
    private static final String video = "video";

    @PostMapping("uploadHead")
    public Return uploadHead(HttpServletRequest request,
                             @RequestParam("file") MultipartFile file) {
        if (file.isEmpty())
            return new Return(Constant.CLIENT_FILE_ERROR, "文件不能为空！");
        else if (file.getContentType() == null || !file.getContentType().startsWith(image))
            return new Return(Constant.CLIENT_FILE_ERROR, "文件格式错误！");
        UserInfo userInfo = (UserInfo) request.getSession().getAttribute(Constant.USERINFO);
        if (fileService.uploadHead(userInfo, file))
            return Return.SUCCESS;
        else
            return new Return(Constant.CLIENT_FILE_ERROR, "上传失败！");
    }

    @GetMapping("getVideoList")
    public Return getVideoList(HttpServletRequest request) {
        UserInfo userInfo = (UserInfo) request.getSession().getAttribute(Constant.USERINFO);
        return new Return<>(fileService.getVideoList(userInfo.getId()));
    }

    @PostMapping("uploadVideo")
    public Return uploadVideo(HttpServletRequest request,
                              @RequestParam("file") MultipartFile file) {
        if (file.isEmpty())
            return new Return(Constant.CLIENT_FILE_ERROR, "文件不能为空！");
        else if (file.getContentType() == null || !file.getContentType().startsWith(video))
            return new Return(Constant.CLIENT_FILE_ERROR, "文件格式错误！");
        UserInfo userInfo = (UserInfo) request.getSession().getAttribute(Constant.USERINFO);
        if (fileService.uploadVideo(userInfo, file))
            return Return.SUCCESS;
        else
            return new Return(Constant.CLIENT_FILE_ERROR, "上传失败！");
    }

}
