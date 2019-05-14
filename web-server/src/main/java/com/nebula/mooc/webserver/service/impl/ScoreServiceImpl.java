/*
 * @author Zhanghh
 * @date 2019-05-14
 */
package com.nebula.mooc.webserver.service.impl;

import com.nebula.mooc.core.entity.Score;
import com.nebula.mooc.webserver.service.ScoreService;
import com.nebula.mooc.webserver.util.TaskUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("ScoreService")
public class ScoreServiceImpl implements ScoreService {

    @Autowired
    private TaskUtil taskUtil;

    public void insertScore(Score score) {
        taskUtil.insertScore(score);
    }

    public void updateScore(Score score) {
        taskUtil.updateScore(score);
    }

}
