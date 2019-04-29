package com.nebula.mooc.webserver.service.impl;

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
    public List<Post> showPostList(Post post) {

        return postDao.showPostList(post);
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
    public boolean postReply(Reply reply)//回复
    {
        return postDao.postReply(reply) > 0;
    }

    @Override
    public boolean delReply(Reply reply)//删除回复
    {
        return postDao.delReply(reply) > 0;
    }

    @Override
    public List<Reply> showReply(Post post) {
        return postDao.showReply(post);
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

}
