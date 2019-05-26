package com.nebula.mooc.webserver.controller;

import com.nebula.mooc.core.Constant;
import com.nebula.mooc.core.entity.*;
import com.nebula.mooc.webserver.service.PostService;
import com.nebula.mooc.webserver.service.ScoreService;
import com.nebula.mooc.webserver.util.CacheUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Caching;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;


/**
 * Created by 15722 on 2019/4/18.
 */
@RestController
@RequestMapping("/api/post/")
public class PostOpController {

    @Autowired
    private PostService postService;

    @Autowired
    private ScoreService scoreService;

    @Caching(evict = {@CacheEvict(value = "showPostList", key = "0"),
            @CacheEvict(value = "showPostList", key = "#kind")})
    @PostMapping("newPost")
    public Return newPost(HttpServletRequest request, Post post, int kind) {
        if (kind < 0 || kind > 10) return new Return(Constant.CLIENT_ERROR_CODE, "参数错误！");
        UserInfo userInfo = CacheUtil.getUserInfo(request);
        post.setUserId(userInfo.getId());
        post.setKindName(Constant.KIND_MAP.get(kind));
        if (postService.newPost(post))
            return Return.SUCCESS;
        else
            return Return.SERVER_ERROR;
    }

    @PostMapping("comment")
    public Return comment(HttpServletRequest request, Reply reply) {
        UserInfo userInfo = CacheUtil.getUserInfo(request);
        reply.setFromId(userInfo.getId());
        if (postService.comment(reply))
            return Return.SUCCESS;
        else
            return Return.SERVER_ERROR;
    }

    @PostMapping("delComment")
    public Return delComment(HttpServletRequest request, Reply reply) {
        UserInfo userInfo = CacheUtil.getUserInfo(request);
        reply.setFromId(userInfo.getId());
        if (postService.delComment(reply))
            return Return.SUCCESS;
        else
            return Return.SERVER_ERROR;
    }

    @PostMapping("replyComment")
    public Return replyComment(HttpServletRequest request, Reply reply) {
        UserInfo userInfo = CacheUtil.getUserInfo(request);
        reply.setFromId(userInfo.getId());
        if (postService.replyComment(reply))
            return new Return(100, "" + postService.lastReplyId());
        else
            return Return.SERVER_ERROR;
    }

    @PostMapping("delReplyComment")
    public Return delReplyComment(HttpServletRequest request, Reply reply) {
        UserInfo userInfo = CacheUtil.getUserInfo(request);
        reply.setFromId(userInfo.getId());
        if (postService.delReplyComment(reply))
            return Return.SUCCESS;
        else
            return Return.SERVER_ERROR;
    }

    @PostMapping("postLike")
    public Return postLike(HttpServletRequest request, Post post) {
        UserInfo userInfo = CacheUtil.getUserInfo(request);
        post.setUserId(userInfo.getId());
        if (postService.ifLike(post)) {
            return new Return(Constant.STAR_LIKE_ALREADY, "您已收藏！");
        }
        if (postService.postLike(post)) {
            scoreService.updatePostScore(new PostScore(userInfo.getId(), post.getId(), Constant.LIKE_SCORE));
            return Return.SUCCESS;
        } else
            return Return.SERVER_ERROR;
    }

    @PostMapping("delLike")
    public Return delLike(HttpServletRequest request, Post post) {
        UserInfo userInfo = CacheUtil.getUserInfo(request);
        post.setUserId(userInfo.getId());
        if (!postService.ifLike(post)) {
            return new Return(Constant.UN_STAR_LIKE, "您未收藏！");
        }
        if (postService.delLike(post)) {
            scoreService.updatePostScore(new PostScore(userInfo.getId(), post.getId(), Constant.VIEW_SCORE));
            return Return.SUCCESS;
        } else
            return Return.SERVER_ERROR;
    }


    @PostMapping("replyStar")
    public Return replyStar(HttpServletRequest request, Reply reply) {
        UserInfo userInfo = CacheUtil.getUserInfo(request);
        reply.setFromId(userInfo.getId());
        if (postService.ifStar(reply)) {
            return new Return(Constant.STAR_LIKE_ALREADY, "您已点赞！");
        }
        if (postService.replyStar(reply)) {
            return Return.SUCCESS;
        } else
            return Return.SERVER_ERROR;
    }

    @PostMapping("delReplyStar")
    public Return delReplyStar(HttpServletRequest request, Reply reply) {
        UserInfo userInfo = CacheUtil.getUserInfo(request);
        reply.setFromId(userInfo.getId());
        if (!postService.ifStar(reply)) {
            return new Return(Constant.UN_STAR_LIKE, "您未点赞！");
        }
        if (postService.delReplyStar(reply))
            return Return.SUCCESS;
        else
            return Return.SERVER_ERROR;
    }

    @PostMapping("postStar")
    public Return postStar(HttpServletRequest request, Post post) {
        UserInfo userInfo = CacheUtil.getUserInfo(request);
        post.setUserId(userInfo.getId());
        if (postService.ifPostStar(post)) {
            return new Return(Constant.STAR_LIKE_ALREADY, "您已点赞！");
        }
        if (postService.postStar(post)) {
            scoreService.updatePostScore(new PostScore(userInfo.getId(), post.getId(), Constant.STAR_SCORE));
            return Return.SUCCESS;
        } else
            return Return.SERVER_ERROR;
    }

    @PostMapping("delPostStar")
    public Return delPostStar(HttpServletRequest request, Post post) {
        UserInfo userInfo = CacheUtil.getUserInfo(request);
        post.setUserId(userInfo.getId());
        if (!postService.ifPostStar(post)) {
            return new Return(Constant.UN_STAR_LIKE, "您未点赞！");
        }
        if (postService.delPostStar(post)) {
            scoreService.updatePostScore(new PostScore(userInfo.getId(), post.getId(), Constant.VIEW_SCORE));
            return Return.SUCCESS;
        } else
            return Return.SERVER_ERROR;
    }

}
