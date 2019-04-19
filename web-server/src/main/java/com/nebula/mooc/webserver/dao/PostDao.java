/*
 * @author Zhanghh
 * @date 2019/4/4
 */
package com.nebula.mooc.webserver.dao;

import com.nebula.mooc.core.entity.Post;
import org.springframework.stereotype.Repository;

@Repository
public interface PostDao {

    int newPost(Post post);


}
