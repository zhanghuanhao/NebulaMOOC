package com.nebula.mooc.webserver.controller;

import com.nebula.mooc.core.Constant;
import com.nebula.mooc.core.entity.*;
import com.nebula.mooc.webserver.service.PostService;
import com.nebula.mooc.webserver.service.ScoreService;
import com.nebula.mooc.webserver.util.CacheUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


/**
 * Created by 15722 on 2019/4/18.
 */
@RestController
@RequestMapping("/sys/post/")
public class PostQueryController {

    @Autowired
    private PostService postService;

    @Autowired
    private ScoreService scoreService;

    private long getUserId(HttpServletRequest request) {
        UserInfo userInfo = CacheUtil.getUserInfo(request);
        if (userInfo != null) return userInfo.getId();
        else return 0;
    }

    @PostMapping("showPost")
    public Return showPost(HttpServletRequest request, Post post) {
        long userId = getUserId(request);
        post.setUserId(userId);
        Post post1 = postService.showPost(post);
        if (post1 != null) {
            if (userId != 0)
                scoreService.updatePostScore(new PostScore(getUserId(request), post.getId(), Constant.LIKE_SCORE));
            return new Return<>(post1);
        }
        return Return.SERVER_ERROR;
    }

    @Cacheable(value = "showPostList", key = "#kind", condition = "#pageIndex == 1")
    @PostMapping("showPostList")
    public Return showPostList(int pageIndex, int kind) {
        Page page = new Page();
        if (pageIndex <= 0 || kind < 0 || kind > 10) return new Return(Constant.CLIENT_ERROR_CODE, "参数错误！");
        page.setKindName(Constant.KIND_MAP.get(kind));
        page.setCurrentPage(pageIndex);
        page.setTotal(postService.postTotal(page));
        page.setPageSize(Constant.PAGE_SIZE);
        if ((page.getCurrentPage() - 1) * page.getPageSize() > page.getTotal())
            page.setCurrentPage(1);
        page.setOffset((page.getCurrentPage() - 1) * page.getPageSize());
        List<Post> postList = postService.showPostList(page);
        if (postList != null) {
            page.setList(postList);
            return new Return<>(page);
        }
        return Return.SERVER_ERROR;
    }

    @PostMapping("showReply")
    public Return showReply(HttpServletRequest request, Page page) {
        page.setUserId(getUserId(request));
        page.setTotal(postService.commentTotal(page));
        page.setPageSize(Constant.PAGE_SIZE);
        if ((page.getCurrentPage() - 1) * page.getPageSize() > page.getTotal())
            page.setCurrentPage(1);
        page.setOffset((page.getCurrentPage() - 1) * page.getPageSize());
        List<Reply> replyList = postService.getComment(page);
        List<Reply> temp;
        if (replyList != null) {
            int le = replyList.size();
            for (int i = 0; i < le; i++) {
                temp = postService.getReply(replyList.get(i));
                if (temp != null) {
                    replyList.addAll(temp);
                }
            }
            page.setList(replyList);
            return new Return<>(page);
        }
        return Return.SERVER_ERROR;
    }


}
