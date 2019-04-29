/*
 * @author Zhanghh
 * @date 2019/4/4
 */
package com.nebula.mooc.webserver.dao;

import com.nebula.mooc.core.entity.Post;
import com.nebula.mooc.core.entity.Reply;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostDao {

    int newPost(Post post);

    int delPost(Post post);

    Post showPost(Post post);

    List<Post> showPostList(Post post);

    int postLike(Post post);

    int delLike(Post post);

    int postReply(Reply reply);

    int delReply(Reply reply);

    int ifStar(Reply reply);

    List<Reply> showReply(Post post);

    int replyStar(Reply reply);

    int delReplyStar(Reply reply);

    int markStar(Reply reply);

    int delMarkStar(Reply reply);

}
