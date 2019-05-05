/*
 * @author Zhanghh
 * @date 2019/4/4
 */
package com.nebula.mooc.webserver.dao;

import com.nebula.mooc.core.entity.Page;
import com.nebula.mooc.core.entity.Post;
import com.nebula.mooc.core.entity.Reply;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostDao {

    int newPost(Post post);

    int delPost(Post post);

    Post showPost(Post post);

    List<Post> showPostList(Page page);

    int postLike(Post post);

    int delLike(Post post);

    int commit(Reply reply);

    int delCommit(Reply reply);

    int ifStar(Reply reply);

    List<Reply> getCommit(Page page);

    List<Reply> getReply(Reply reply);

    int replyCommit(Reply reply);

    int delReplyCommit(Reply reply);

    int replyStar(Reply reply);

    int delReplyStar(Reply reply);

    int markStar(Reply reply);

    int delMarkStar(Reply reply);

    int lastReplyId();

}
