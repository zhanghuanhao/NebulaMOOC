package com.nebula.mooc.webserver.controller;

import com.nebula.mooc.core.entity.Return;
import com.nebula.mooc.webserver.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;


/**
 * Created by 15722 on 2019/4/18.
 */
@RestController
@RequestMapping("/sys/post/")
public class PostController {
    @Autowired
    private PostService postService;

    @PostMapping("showPost")
    public Return showPost() {
        return Return.SERVER_ERROR;
    }

    @PostMapping("showPostList")
    public Return showPostList() {
        return Return.SERVER_ERROR;
    }

    @PostMapping("newPost")
    public Return newPost(HttpServletRequest request) {
        if (postService.newPost(request)) return Return.SUCCESS;
        else return Return.SERVER_ERROR;
    }

    @PostMapping("delPost")
    public Return delPost(HttpServletRequest request) {
        if (postService.delPost(request)) return Return.SUCCESS;
        else return Return.SERVER_ERROR;
    }


    @PostMapping("postReply")
    public Return postReply(HttpServletRequest request) {
        if (postService.postReply(request)) return Return.SUCCESS;
        else return Return.SERVER_ERROR;
    }

    @PostMapping("delReply")
    public Return delReply(HttpServletRequest request) {
        if (postService.delReply(request)) return Return.SUCCESS;
        else return Return.SERVER_ERROR;
    }

    @PostMapping("postLike")
    public Return postLike(HttpServletRequest request) {
        if (postService.postLike(request)) return Return.SUCCESS;
        else return Return.SERVER_ERROR;
    }

    @PostMapping("delLike")
    public Return delLike(HttpServletRequest request) {
        if (postService.delLike(request)) return Return.SUCCESS;
        else return Return.SERVER_ERROR;
    }


    @PostMapping("replyStar")
    public Return replyStar(HttpServletRequest request) {
        if (postService.replyStar(request)) return Return.SUCCESS;
        else return Return.SERVER_ERROR;
    }

    @PostMapping("delReplyStar")
    public Return delReplyStar(HttpServletRequest request) {
        if (postService.delReplyStar(request)) return Return.SUCCESS;
        else return Return.SERVER_ERROR;
    }

}
