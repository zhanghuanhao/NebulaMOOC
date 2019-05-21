/*
 * @author Zhanghh
 * @date 2019/5/10
 */
package com.nebula.mooc.webserver.dao;

import com.nebula.mooc.core.entity.UserInfo;
import com.nebula.mooc.core.entity.Video;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FileDao {

    int updateHeadUrl(UserInfo userInfo);

    String getHeadUrl(@Param("id") long id);

    List<Video> getVideoList(@Param("userId") long userId);

    int addVideo(Video video);

    int updateVideo(Video video);

    int removeVideo(Video video);

}
