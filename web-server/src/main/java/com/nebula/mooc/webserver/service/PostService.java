package com.nebula.mooc.webserver.service;


import com.nebula.mooc.core.entity.Post;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by 15722 on 2019/4/18.
 */

public interface PostService {


    boolean showPost(HttpServletRequest request);

    boolean showPostList(HttpServletRequest request);

    /**
     * 新建贴子
     *
     * @param
     * @return
     */
    boolean newPost(Post post);

    boolean delPost(HttpServletRequest request);

    boolean postReply(HttpServletRequest request);

    boolean delReply(HttpServletRequest request);

    boolean postLike(HttpServletRequest request);

    boolean delLike(HttpServletRequest request);

    boolean replyStar(HttpServletRequest request);

    boolean delReplyStar(HttpServletRequest request);


}
