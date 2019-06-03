package com.nebula.mooc.webserver.controller;

import com.nebula.mooc.core.Constant;
import com.nebula.mooc.core.entity.Post;
import com.nebula.mooc.core.entity.Reply;
import com.nebula.mooc.core.entity.Return;
import com.nebula.mooc.core.entity.UserInfo;
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

    @Caching(evict = {@CacheEvict(value = "showPostList", key = "'TOTAL'"),
            @CacheEvict(value = "showPostList", keyGenerator = "kindMapKeyGenerator")})
    @PostMapping("newPost")
    public Return newPost(int kind, HttpServletRequest request, Post post) {
        if (kind < 0 || kind > 10) return Return.CLIENT_PARAM_ERROR;
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

    @Caching(evict = {@CacheEvict(value = "showPostList", key = "#post.kindName", condition = "#post.kindName != null"),
            @CacheEvict(value = "showPostList", key = "'TOTAL'")})
    @PostMapping("postLike")
    public Return postLike(HttpServletRequest request, Post post) {
        UserInfo userInfo = CacheUtil.getUserInfo(request);
        post.setUserId(userInfo.getId());
        if (postService.ifLike(post)) {
            return Return.STAR_LIKE_ALREADY;
        }
        if (postService.postLike(post)) {
            scoreService.incrPost(userInfo.getId(), post.getId(), Constant.LIKE_SCORE);
            return Return.SUCCESS;
        } else
            return Return.SERVER_ERROR;
    }

    @Caching(evict = {@CacheEvict(value = "showPostList", key = "#post.kindName", condition = "#post.kindName != null"),
            @CacheEvict(value = "showPostList", key = "'TOTAL'")})
    @PostMapping("delLike")
    public Return delLike(HttpServletRequest request, Post post) {
        UserInfo userInfo = CacheUtil.getUserInfo(request);
        post.setUserId(userInfo.getId());
        if (!postService.ifLike(post)) {
            return Return.UN_STAR_LIKE;
        }
        if (postService.delLike(post)) {
            scoreService.decrPost(userInfo.getId(), post.getId(), Constant.LIKE_SCORE);
            return Return.SUCCESS;
        } else
            return Return.SERVER_ERROR;
    }

    @PostMapping("replyStar")
    public Return replyStar(HttpServletRequest request, Reply reply) {
        UserInfo userInfo = CacheUtil.getUserInfo(request);
        reply.setFromId(userInfo.getId());
        if (postService.ifStar(reply)) {
            return Return.STAR_LIKE_ALREADY;
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
            return Return.UN_STAR_LIKE;
        }
        if (postService.delReplyStar(reply))
            return Return.SUCCESS;
        else
            return Return.SERVER_ERROR;
    }

    @Caching(evict = {@CacheEvict(value = "showPostList", key = "#post.kindName", condition = "#post.kindName != null"),
            @CacheEvict(value = "showPostList", key = "'TOTAL'")})
    @PostMapping("postStar")
    public Return postStar(HttpServletRequest request, Post post) {
        UserInfo userInfo = CacheUtil.getUserInfo(request);
        post.setUserId(userInfo.getId());
        if (postService.ifPostStar(post)) {
            return Return.STAR_LIKE_ALREADY;
        }
        if (postService.postStar(post)) {
            scoreService.incrPost(userInfo.getId(), post.getId(), Constant.STAR_SCORE);
            return Return.SUCCESS;
        } else
            return Return.SERVER_ERROR;
    }

    @Caching(evict = {@CacheEvict(value = "showPostList", key = "#post.kindName", condition = "#post.kindName != null"),
            @CacheEvict(value = "showPostList", key = "'TOTAL'")})
    @PostMapping("delPostStar")
    public Return delPostStar(HttpServletRequest request, Post post) {
        UserInfo userInfo = CacheUtil.getUserInfo(request);
        post.setUserId(userInfo.getId());
        if (!postService.ifPostStar(post)) {
            return Return.UN_STAR_LIKE;
        }
        if (postService.delPostStar(post)) {
            scoreService.decrPost(userInfo.getId(), post.getId(), Constant.STAR_SCORE);
            return Return.SUCCESS;
        } else
            return Return.SERVER_ERROR;
    }

}
