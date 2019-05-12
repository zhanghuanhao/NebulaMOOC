package com.nebula.mooc.webserver.controller;

import com.nebula.mooc.core.Constant;
import com.nebula.mooc.core.entity.*;
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
    public Return showPost(HttpServletRequest request, Post post) {
        UserInfo userInfo = (UserInfo) request.getSession().getAttribute(Constant.USERINFO);
        if (userInfo != null) {
            post.setUserId(userInfo.getId());
            Post post1 = postService.showPost(post);
            if (post1 != null) return new Return<Post>(post1);
        }
        return Return.SERVER_ERROR;
    }

    @PostMapping("showPostList")
    public Return showPostList(Page page) {
        page.setTotal(postService.postTotal());
        page.setPageSize(10);
        if ((page.getCurrentPage() - 1) * page.getPageSize() > page.getTotal())
            page.setCurrentPage(1);
        page.setOffset((page.getCurrentPage() - 1) * page.getPageSize());
        List<Post> postList = postService.showPostList(page);
        if (postList != null) {
            page.setList(postList);
            return new Return<Page>(page);
        }
        return Return.SERVER_ERROR;
    }

    @PostMapping("newPost")
    public Return newPost(HttpServletRequest request, Post post) {
        UserInfo userInfo = (UserInfo) request.getSession().getAttribute(Constant.USERINFO);
        if (userInfo != null) {
            post.setUserId(userInfo.getId());
            if (postService.newPost(post))
                return Return.SUCCESS;
        }
        return Return.SERVER_ERROR;
    }

    @PostMapping("delPost")
    public Return delPost(HttpServletRequest request, Post post) {
        UserInfo userInfo = (UserInfo) request.getSession().getAttribute(Constant.USERINFO);
        if (userInfo != null) {
            post.setUserId(userInfo.getId());
            if (postService.delPost(post))
                return Return.SUCCESS;
        }
        return Return.SERVER_ERROR;
    }


    @PostMapping("commit")
    public Return commit(HttpServletRequest request, Reply reply) {
        UserInfo userInfo = (UserInfo) request.getSession().getAttribute(Constant.USERINFO);
        if (userInfo != null) {
            reply.setFromId(userInfo.getId());
            if (postService.commit(reply))
                return Return.SUCCESS;
        }
        return Return.SERVER_ERROR;
    }

    @PostMapping("delCommit")
    public Return delCommit(HttpServletRequest request, Reply reply) {
        UserInfo userInfo = (UserInfo) request.getSession().getAttribute(Constant.USERINFO);
        if (userInfo != null) {
            reply.setFromId(userInfo.getId());
            if (postService.delCommit(reply))
                return Return.SUCCESS;
        }
        return Return.SERVER_ERROR;
    }

    @PostMapping("replyCommit")
    public Return replyCommit(HttpServletRequest request, Reply reply) {
        UserInfo userInfo = (UserInfo) request.getSession().getAttribute(Constant.USERINFO);
        if (userInfo != null) {
            reply.setFromId(userInfo.getId());
            if (postService.replyCommit(reply))
                return new Return(100, "" + postService.lastReplyId());
        }
        return Return.SERVER_ERROR;
    }

    @PostMapping("delReplyCommit")
    public Return delReplyCommit(HttpServletRequest request, Reply reply) {
        UserInfo userInfo = (UserInfo) request.getSession().getAttribute(Constant.USERINFO);
        if (userInfo != null) {
            reply.setFromId(userInfo.getId());
            if (postService.delReplyCommit(reply))
                return Return.SUCCESS;
        }
        return Return.SERVER_ERROR;
    }

    @PostMapping("showReply")
    public Return showReply(HttpServletRequest request, Page page) {
        UserInfo userInfo = (UserInfo) request.getSession().getAttribute(Constant.USERINFO);
        if (userInfo != null) {
            page.setUserId(userInfo.getId());
            page.setTotal(postService.commitTotal(page));
            page.setPageSize(10);
            if ((page.getCurrentPage() - 1) * page.getPageSize() > page.getTotal())
                page.setCurrentPage(1);
            page.setOffset((page.getCurrentPage() - 1) * page.getPageSize());
            List<Reply> replyList = postService.getCommit(page);
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
                return new Return<Page>(page);
            }
        }
        return Return.SERVER_ERROR;
    }

    @PostMapping("postLike")
    public Return postLike(HttpServletRequest request, Post post) {
        UserInfo userInfo = (UserInfo) request.getSession().getAttribute(Constant.USERINFO);
        if (userInfo != null) {
            post.setUserId(userInfo.getId());
            if (postService.ifLike(post)) {
                return new Return(105, "您已收藏！");
            }
            if (postService.postLike(post))
                return Return.SUCCESS;
        }
        return Return.SERVER_ERROR;
    }

    @PostMapping("delLike")
    public Return delLike(HttpServletRequest request, Post post) {
        UserInfo userInfo = (UserInfo) request.getSession().getAttribute(Constant.USERINFO);
        if (userInfo != null) {
            post.setUserId(userInfo.getId());
            if (!postService.ifLike(post)) {
                return new Return(106, "您未收藏！");
            }
            if (postService.delLike(post))
                return Return.SUCCESS;
        }
        return Return.SERVER_ERROR;
    }


    @PostMapping("replyStar")
    public Return replyStar(HttpServletRequest request, Reply reply) {
        UserInfo userInfo = (UserInfo) request.getSession().getAttribute(Constant.USERINFO);
        if (userInfo != null) {
            reply.setFromId(userInfo.getId());
            if (postService.ifStar(reply)) {
                return new Return(105, "您已点赞！");
            }
            if (postService.replyStar(reply))
                return Return.SUCCESS;
        }
        return Return.SERVER_ERROR;
    }

    @PostMapping("delReplyStar")
    public Return delReplyStar(HttpServletRequest request, Reply reply) {
        UserInfo userInfo = (UserInfo) request.getSession().getAttribute(Constant.USERINFO);
        if (userInfo != null) {
            reply.setFromId(userInfo.getId());
            if (!postService.ifStar(reply)) {
                return new Return(106, "您未点赞！");
            }
            if (postService.delReplyStar(reply))
                return Return.SUCCESS;
        }
        return Return.SERVER_ERROR;
    }

}
