/*
 * @author Zhanghh
 * @date 2019/5/24
 */
package com.nebula.mooc.liveserver.controller;

import com.nebula.mooc.core.entity.UserInfo;
import com.nebula.mooc.liveserver.service.LiveService;
import com.nebula.mooc.liveserver.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping(value = "/callback/")
public class CallbackController {

    @Autowired
    private UserService userService;

    @Autowired
    private LiveService liveService;

    private void checkLogin(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String liveToken = request.getParameter("name");
        String userToken = request.getParameter("token");
        System.out.println(request.getQueryString());
        // 1. 检查参数
        if (userToken != null && liveToken != null) {
            // 2. 检查用户是否登录
            if (userService.loginCheck(userToken)) {
                UserInfo userInfo = userService.getUserInfo(userToken);
                // 3. 检查用户对应的房间token是否正确
                if (liveService.checkToken(userInfo.getId(), liveToken))
                    return; // 检查通过
            }
        }
        response.sendError(500);
    }

    @GetMapping(value = "on_publish")
    public void onPublish(HttpServletRequest request, HttpServletResponse response) throws Exception {
        checkLogin(request, response);
    }

    @GetMapping(value = "on_publish_done")
    public void onPublishDone() {
    }

    @GetMapping(value = "on_update")
    public void onUpdate(HttpServletRequest request, HttpServletResponse response) throws Exception {
        checkLogin(request, response);
    }

}
