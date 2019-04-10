/*
 * @author Zhanghh
 * @date 2019/4/8
 */
package com.nebula.mooc.webserver.service.Impl;

import com.nebula.mooc.webserver.dao.TestDao;
import com.nebula.mooc.webserver.service.TestService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class TestServiceImpl implements TestService {

    @Resource
    private TestDao testDao;

    @Override
    public String getNickName() {
        return testDao.getNickName();
    }
}
