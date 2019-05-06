package com.nebula.mooc.webserver.service.impl;

import com.nebula.mooc.core.entity.Page;
import com.nebula.mooc.core.entity.Post;
import com.nebula.mooc.core.entity.Reply;
import com.nebula.mooc.webserver.dao.PostDao;
import com.nebula.mooc.webserver.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by 15722 on 2019/4/18.
 */
@Service("PostService")
public class PostServiceImpl implements PostService {

    @Autowired
    private PostDao postDao;

    @Override
    public Post showPost(Post post) {
        return postDao.showPost(post);
    }

    @Override
    public List<Post> showPostList(Page page) {
        return postDao.showPostList(page);
    }


    @Override
    public boolean newPost(Post post)//发贴
    {
        return postDao.newPost(post) > 0;
    }

    @Override
    public boolean delPost(Post post) //删除贴子
    {
        return postDao.delPost(post) > 0;
    }

    @Override
    public boolean commit(Reply reply)//回复
    {
        return postDao.commit(reply) > 0;
    }

    @Override
    public boolean delCommit(Reply reply)//删除回复
    {
        return postDao.delCommit(reply) > 0;
    }

    @Override
    public List<Reply> getCommit(Page page) {
        return postDao.getCommit(page);
    }

    @Override
    public boolean replyCommit(Reply reply) {
        return postDao.replyCommit(reply) > 0;
    }

    @Override
    public boolean delReplyCommit(Reply reply) {
        return postDao.delReplyCommit(reply) > 0;
    }

    @Override
    public List<Reply> getReply(Reply reply) {
        return postDao.getReply(reply);
    }


    @Override
    public boolean postLike(Post post)//收藏贴子
    {
        return postDao.postLike(post) > 0;
    }

    @Override
    public boolean delLike(Post post) {//取消收藏

        return postDao.delLike(post) > 0;
    }

    @Override
    public boolean replyStar(Reply reply)//点赞回复
    {
        return postDao.markStar(reply) + postDao.replyStar(reply) > 1;
    }

    @Override
    public boolean delReplyStar(Reply reply)//取消点赞回复
    {
        return postDao.delMarkStar(reply) + postDao.delReplyStar(reply) > 1;
    }

    @Override
    public boolean ifStar(Reply reply)//查询是否曾经点赞
    {
        return postDao.ifStar(reply) > 0;
    }

    @Override
    public int lastReplyId() {
        return postDao.lastReplyId();
    }

    @Override
    public int postTotal() {
        return postDao.postTotal();
    }

    @Override
    public int commitTotal(Page page) {
        return postDao.commitTotal(page);
    }

}
