package com.nebula.mooc.webserver.service;


import com.nebula.mooc.core.entity.Page;
import com.nebula.mooc.core.entity.Post;
import com.nebula.mooc.core.entity.Reply;

import java.util.List;

/**
 * Created by 15722 on 2019/4/18.
 */

public interface PostService {


    Post showPost(Post post);

    List<Post> showPostList(Page page);

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

    boolean comment(Reply reply);

    boolean delComment(Reply reply);

    boolean replyComment(Reply reply);

    boolean delReplyComment(Reply reply);

    List<Reply> getComment(Page page);

    List<Reply> getReply(Reply reply);

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

    boolean ifLike(Post post);

    boolean replyStar(Reply reply);

    boolean delReplyStar(Reply reply);

    int lastReplyId();

    int postTotal();

    int commentTotal(Page page);

    boolean ifPostStar(Post post);

    boolean postStar(Post post);

    boolean delPostStar(Post post);

}
