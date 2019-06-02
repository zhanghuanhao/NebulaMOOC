/*
 * @author Zhanghh
 * @date 2019/5/24
 */
package com.nebula.mooc.liveserver.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping(value = "/callback/")
public class CallbackController {

    @GetMapping(value = "on_connect")
    public String onConnect(HttpServletRequest request, HttpServletResponse response) throws IOException {
        debug(request, "on_connect");
        request.getSession(false);  //创建session
//        response.sendError(500);
        return "on_connect";
    }

    @GetMapping(value = "on_play")
    public String onPlay(HttpServletRequest request) {
        debug(request, "on_play");
        return "on_play";
    }

    @GetMapping(value = "on_publish")
    public String onPublish(HttpServletRequest request) {
        debug(request, "on_publish");
        return "on_publish";
    }

    @GetMapping(value = "on_done")
    public String onDone(HttpServletRequest request) {
        debug(request, "on_done");
        return "on_done";
    }

    @GetMapping(value = "on_play_done")
    public String onPlayDone(HttpServletRequest request) {
        debug(request, "on_play_done");
        return "on_play_done";
    }

    @GetMapping(value = "on_publish_done")
    public String onPublishDone(HttpServletRequest request) {
        debug(request, "on_publish_done");
        return "on_publish_done";
    }

    @GetMapping(value = "on_record_done")
    public String onRecordDone(HttpServletRequest request) {
        debug(request, "on_record_done");
        return "on_record_done";
    }

    @GetMapping(value = "on_update")
    public String onUpdate(HttpServletRequest request) {
        debug(request, "on_update");
        return "on_update";
    }

    private String debug(HttpServletRequest request, String action) {
        String str = action + ": " + request.getRequestURI() + " " + request.getQueryString();
        System.out.println(str);
        System.out.println(request.getParameter("addr"));
        return str;
    }

}
