/*
 * @author Zhanghh
 * @date 2019/5/10
 */
package com.nebula.mooc.webserver.dao;

import com.nebula.mooc.core.entity.UserInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface FileDao {

    int updateHeadUrl(UserInfo userInfo);

    String getHeadUrl(@Param("id") long id);
}
