package com.nebula.mooc.webserver.service.impl;

import com.nebula.mooc.core.Constant;
import com.nebula.mooc.core.entity.Page;
import com.nebula.mooc.core.entity.Post;
import com.nebula.mooc.core.entity.Reply;
import com.nebula.mooc.webserver.dao.PostDao;
import com.nebula.mooc.webserver.service.PostService;
import com.nebula.mooc.webserver.util.RecommendUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 15722 on 2019/4/18.
 */
@Service("PostService")
public class PostServiceImpl implements PostService {

    @Autowired
    private PostDao postDao;

    @Autowired
    private RecommendUtil recommendUtil;

    @Override
    public Post showPost(Post post) {
        return postDao.showPost(post);
    }

    @Override
    public List<Post> showPostList(Page page) {
        return postDao.showPostList(page);
    }

    @Override
    public List<Post> showHotPostList() {
        return postDao.showHotPostList(Constant.PAGE_SIZE);
    }

    @Override
    public List showHomePostList() {
        List<List> postList = new ArrayList<>(2);
        // 最新课程
        Page page = new Page();
        page.setOffset(0);
        page.setKindName("TOTAL");
        page.setPageSize(Constant.HOME_PAGE_SIZE);
        postList.add(postDao.showPostList(page));
        // 最热课程
        postList.add(postDao.showHotPostList(Constant.HOME_PAGE_SIZE));
        return postList;
    }

    @Override
    public List showRecommendPostList(long userId) {
        Object[] postIds = recommendUtil.recommendPost(userId);
        if (postIds == null || postIds.length == 0) return null;
        List<Post> posts = new ArrayList<>(postIds.length);
        for (Object postId : postIds) {
            Post post = new Post();
            post.setId((Long) postId);
            post.setUserId(userId);
            posts.add(postDao.showPost(post));
        }
        return posts;
    }

    @Override
    public boolean newPost(Post post)//发贴
    {
        return postDao.newPost(post) + postDao.addTotal(post) > 1;
    }

    @Override
    public boolean comment(Reply reply)//回复
    {
        return postDao.comment(reply) > 0;
    }

    @Override
    public boolean delComment(Reply reply)//删除回复
    {
        return postDao.delComment(reply) > 0;
    }

    @Override
    public List<Reply> getComment(Page page) {
        return postDao.getComment(page);
    }

    @Override
    public boolean replyComment(Reply reply) {
        return postDao.replyComment(reply) > 0;
    }

    @Override
    public boolean delReplyComment(Reply reply) {
        return postDao.delReplyComment(reply) > 0;
    }

    @Override
    public List<Reply> getReply(Reply reply) {
        return postDao.getReply(reply);
    }


    @Override
    public boolean postLike(Post post)//收藏贴子
    {
        return postDao.postLike(post) + postDao.addLike(post) > 1;
    }

    @Override
    public boolean delLike(Post post) {//取消收藏

        return postDao.delLike(post) + postDao.subLike(post) > 1;
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
    public boolean ifLike(Post post) {
        return postDao.ifLike(post) > 0;
    }

    @Override
    public int lastReplyId() {
        return postDao.lastReplyId();
    }

    @Override
    public int postTotal(Page page) {
        return postDao.postTotal(page);
    }

    @Override
    public int commentTotal(Page page) {
        return postDao.commentTotal(page);
    }

    @Override
    public boolean ifPostStar(Post post) {
        return postDao.ifPostStar(post) > 0;
    }

    @Override
    public boolean postStar(Post post) {
        return postDao.markPostStar(post) + postDao.postStar(post) > 1;
    }

    @Override
    public boolean delPostStar(Post post) {
        return postDao.delMarkPostStar(post) + postDao.delPostStar(post) > 1;
    }

    @Override
    public List<Post> getLikePost(Page page) {
        return postDao.getLikePost(page);
    }

    @Override
    public int likePostTotal(Page page) {
        return postDao.likePostTotal(page);
    }
}
