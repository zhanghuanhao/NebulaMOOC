/*
 * @author Zhanghh
 * @date 2019/4/4
 */
package com.nebula.mooc.dao;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TestDao {

    int count();
}
