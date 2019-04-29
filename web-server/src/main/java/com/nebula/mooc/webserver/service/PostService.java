package com.nebula.mooc.webserver.service;


import com.nebula.mooc.core.entity.Post;
import com.nebula.mooc.core.entity.Reply;

import java.util.List;

/**
 * Created by 15722 on 2019/4/18.
 */

public interface PostService {


    Post showPost(Post post);

    List<Post> showPostList(Post post);

    /**
     * 新建贴子
     *
     * @param post
     * @return 返回是否新建成功
     */
    boolean newPost(Post post);

    /**
     * 删除贴子
     *
     * @param post
     * @return 返回是否删除成功
     */
    boolean delPost(Post post);

    boolean postReply(Reply reply);

    boolean delReply(Reply reply);

    List<Reply> showReply(Post post);

    /**
     * 收藏贴子
     *
     * @param post
     * @return 返回是否收藏成功
     */
    boolean postLike(Post post);

    /**
     * 取消收藏
     *
     * @param post
     * @return 返回是否取消收藏成功
     */
    boolean delLike(Post post);

    boolean ifStar(Reply reply);

    boolean replyStar(Reply reply);

    boolean delReplyStar(Reply reply);

    int lastReplyId();

}
