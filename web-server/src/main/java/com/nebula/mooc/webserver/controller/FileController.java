/*
 * @author Zhanghh
 * @date 2019/5/7
 */
package com.nebula.mooc.webserver.controller;

import com.nebula.mooc.core.Constant;
import com.nebula.mooc.core.entity.Return;
import com.nebula.mooc.core.entity.UserInfo;
import com.nebula.mooc.core.service.UserService;
import com.nebula.mooc.core.util.TokenUtil;
import com.nebula.mooc.webserver.util.CookieUtil;
import com.nebula.mooc.webserver.util.ImgUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@RestController
@RequestMapping("/file/")
public class FileController {

    @Autowired
    private UserService userService;

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
        String fileName = file.getOriginalFilename();
        fileName = TokenUtil.generateFileName(userInfo, fileName);

        //转换并压缩图片
//        System.out.println("开始：" + new Date().toLocaleString());
//        ImgUtil.resize(file.getInputStream(), Constant.CLASSPATH + Constant.HEAD_PATH + fileName + ".png");
//        System.out.println("结束：" + new Date().toLocaleString());

//        File dest = new File(Constant.CLASSPATH + Constant.HEAD_PATH + fileName + ".png");
        try {
            System.out.println("开始：" + System.currentTimeMillis());
            ImgUtil.resize(file.getInputStream(), Constant.CLASSPATH + Constant.HEAD_PATH + fileName + ".png");
            System.out.println("结束：" + System.currentTimeMillis());
            return new Return(Constant.SUCCESS_CODE, "上传成功！");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new Return(Constant.CLIENT_FILE_ERROR, "上传失败！");
    }

}
