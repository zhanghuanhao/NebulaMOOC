/*
 * @author Zhanghh
 * @date 2019/5/7
 */
package com.nebula.mooc.webserver.controller;

import com.nebula.mooc.core.Constant;
import com.nebula.mooc.core.entity.Return;
import com.nebula.mooc.core.entity.UserInfo;
import com.nebula.mooc.core.service.UserService;
import com.nebula.mooc.webserver.service.FileService;
import com.nebula.mooc.webserver.util.CookieUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/file/")
public class FileController {

    @Autowired
    private UserService userService;

    @Autowired
    private FileService fileService;

    private static final String image = "image";

    @PostMapping("uploadHeadImg")
    public Return uploadHeadImg(HttpServletRequest request,
                                @RequestParam("file") MultipartFile file) {
        if (file.isEmpty())
            return new Return(Constant.CLIENT_FILE_ERROR, "文件不能为空！");
        else if (file.getContentType() == null || !file.getContentType().startsWith(image))
            return new Return(Constant.CLIENT_FILE_ERROR, "文件格式错误！");
        String token = CookieUtil.get(request, Constant.TOKEN);
        UserInfo userInfo = userService.getUserInfo(token);
        if (fileService.uploadHead(userInfo, file))
            return Return.SUCCESS;
        return new Return(Constant.CLIENT_FILE_ERROR, "上传失败！");
    }

}
