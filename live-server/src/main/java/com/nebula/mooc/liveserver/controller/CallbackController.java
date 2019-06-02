/*
 * @author Zhanghh
 * @date 2019/5/24
 */
package com.nebula.mooc.liveserver.controller;

import com.nebula.mooc.liveserver.core.LiveManager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping(value = "/callback/")
public class CallbackController {

    @GetMapping(value = "on_publish")
    public void onPublish(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String liveToken = request.getParameter("name");
        String userToken = request.getParameter("token");
        System.out.println(request.getQueryString());
        if (userToken == null || liveToken == null) response.sendError(500);
        // 未申请直播
        if (!LiveManager.checkToken(userToken, liveToken)) response.sendError(500);
    }

    @GetMapping(value = "on_publish_done")
    public void onPublishDone() {
    }

    @GetMapping(value = "on_update")
    public void onUpdate(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String liveToken = request.getParameter("name");
        String userToken = request.getParameter("token");
        if (userToken == null || liveToken == null) response.sendError(500);
        // 未申请直播
        if (!LiveManager.checkToken(userToken, liveToken)) response.sendError(500);
    }

}
