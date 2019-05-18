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

    int addLike(Post post);

    int subLike(Post post);

    int comment(Reply reply);

    int delComment(Reply reply);

    int ifStar(Reply reply);

    int ifLike(Post post);

    List<Reply> getComment(Page page);

    List<Reply> getReply(Reply reply);

    int replyComment(Reply reply);

    int delReplyComment(Reply reply);

    int replyStar(Reply reply);

    int delReplyStar(Reply reply);

    int markStar(Reply reply);

    int delMarkStar(Reply reply);

    int lastReplyId();

    int postTotal();

    int commentTotal(Page page);

    int markPostStar(Post post);

    int delMarkPostStar(Post post);

    int ifPostStar(Post post);

    int postStar(Post post);

    int delPostStar(Post post);

    int addTotal();

    int subTotal();

}
