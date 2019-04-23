package com.nebula.mooc.webserver.controller;

import com.nebula.mooc.core.entity.Post;
import com.nebula.mooc.core.entity.Reply;
import com.nebula.mooc.core.entity.Return;
import com.nebula.mooc.core.entity.UserInfo;
import com.nebula.mooc.webserver.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


/**
 * Created by 15722 on 2019/4/18.
 */
@RestController
@RequestMapping("/api/post/")
public class PostController {
    @Autowired
    private PostService postService;

    @PostMapping("showPost")
    public Return showPost(Post post) {
        Post post1 = postService.showPost(post);
        if (post1 != null) return new Return<Post>(post1);
        return Return.SERVER_ERROR;
    }

    @PostMapping("showPostList")
    public Return showPostList(Post post) {
        List<Post> postList = postService.showPostList(post);
        if (postList != null) return new Return<List<Post>>(postList);
        return Return.SERVER_ERROR;
    }

    @PostMapping("newPost")
    public Return newPost(HttpServletRequest request, Post post) {
        UserInfo userInfo = (UserInfo) request.getAttribute("userInfo");
        if (userInfo != null) {
            post.setUserId(userInfo.getId());
            if (postService.newPost(post))
                return Return.SUCCESS;
        }
        return Return.SERVER_ERROR;
    }

    @PostMapping("delPost")
    public Return delPost(HttpServletRequest request, Post post) {
        UserInfo userInfo = (UserInfo) request.getAttribute("userInfo");
        if (userInfo != null) {
            post.setUserId(userInfo.getId());
            if (postService.delPost(post))
                return Return.SUCCESS;
        }
        return Return.SERVER_ERROR;
    }


    @PostMapping("postReply")
    public Return postReply(HttpServletRequest request, Reply reply) {
        UserInfo userInfo = (UserInfo) request.getAttribute("userInfo");
        if (userInfo != null) {
            reply.setUserId(userInfo.getId());
            if (postService.postReply(reply))
                return Return.SUCCESS;
        }
        return Return.SERVER_ERROR;
    }

    @PostMapping("delReply")
    public Return delReply(HttpServletRequest request, Reply reply) {
        UserInfo userInfo = (UserInfo) request.getAttribute("userInfo");
        if (userInfo != null) {
            reply.setUserId(userInfo.getId());
            if (postService.delReply(reply))
                return Return.SUCCESS;
        }
        return Return.SERVER_ERROR;
    }

    @PostMapping("showReply")
    public Return showReply(Post post) {
        List<Reply> replyList = postService.showReply(post);
        if (replyList != null) return new Return<List<Reply>>(replyList);
        return Return.SERVER_ERROR;
    }

    @PostMapping("postLike")
    public Return postLike(HttpServletRequest request, Post post) {
        UserInfo userInfo = (UserInfo) request.getAttribute("userInfo");
        if (userInfo != null) {
            post.setUserId(userInfo.getId());
            if (postService.postLike(post))
                return Return.SUCCESS;
        }
        return Return.SERVER_ERROR;
    }

    @PostMapping("delLike")
    public Return delLike(HttpServletRequest request, Post post) {
        UserInfo userInfo = (UserInfo) request.getAttribute("userInfo");
        if (userInfo != null) {
            post.setUserId(userInfo.getId());
            if (postService.delLike(post))
                return Return.SUCCESS;
        }
        return Return.SERVER_ERROR;
    }


    @PostMapping("replyStar")
    public Return replyStar(HttpServletRequest request, Reply reply) {
        UserInfo userInfo = (UserInfo) request.getAttribute("userInfo");
        if (userInfo != null) {
            reply.setUserId(userInfo.getId());
            if (postService.replyStar(reply))
                return Return.SUCCESS;
        }
        return Return.SERVER_ERROR;
    }

    @PostMapping("delReplyStar")
    public Return delReplyStar(HttpServletRequest request, Reply reply) {
        UserInfo userInfo = (UserInfo) request.getAttribute("userInfo");
        if (userInfo != null) {
            reply.setUserId(userInfo.getId());
            if (postService.delReplyStar(reply))
                return Return.SUCCESS;
        }
        return Return.SERVER_ERROR;
    }

}
