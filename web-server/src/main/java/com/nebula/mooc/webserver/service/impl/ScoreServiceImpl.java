/*
 * @author Zhanghh
 * @date 2019-05-14
 */
package com.nebula.mooc.webserver.service.impl;

import com.nebula.mooc.core.entity.Score;
import com.nebula.mooc.webserver.config.TaskConfig;
import com.nebula.mooc.webserver.service.ScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("ScoreService")
public class ScoreServiceImpl implements ScoreService {

    @Autowired
    private TaskConfig taskConfig;

    public void insertScore(Score score) {
        TaskConfig.ScoreTask scoreTask = taskConfig.new
                ScoreTask(score, TaskConfig.DaoType.INSERT);
        taskConfig.submit(scoreTask);
    }

    public void updateScore(Score score) {
        TaskConfig.ScoreTask scoreTask = taskConfig.new
                ScoreTask(score, TaskConfig.DaoType.UPDATE);
        taskConfig.submit(scoreTask);
    }

}
