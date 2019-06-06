/*
 * @author Zhanghh
 * @date 2019/5/24
 */
package com.nebula.mooc.liveserver.controller;

import com.nebula.mooc.liveserver.service.LiveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping(value = "/callback/")
public class CallbackController {

    @Autowired
    private LiveService liveService;

    private void checkLogin(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String liveId = request.getParameter("name");    // 房间号，即用户id
        String liveToken = request.getParameter("token");   // 房间Token
        System.out.println(request.getQueryString());
        // 1. 检查参数
        if (liveToken != null && liveId != null) {
            // 2. 检查用户推流房间ID（用户ID）对应的房间Token是否正确
            try {
                long userId = Long.valueOf(liveId);
                if (liveService.checkToken(userId, liveToken))
                    return; // 检查通过
            } catch (NumberFormatException e) {
            }
        }
        response.sendError(500);
    }

    @GetMapping(value = "on_publish")
    public void onPublish(HttpServletRequest request, HttpServletResponse response) throws IOException {
        checkLogin(request, response);
    }

    @GetMapping(value = "on_publish_done")
    public void onPublishDone() {
    }

    @GetMapping(value = "on_update")
    public void onUpdate(HttpServletRequest request, HttpServletResponse response) throws IOException {
        checkLogin(request, response);
    }

}
