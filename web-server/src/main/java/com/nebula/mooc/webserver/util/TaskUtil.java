/*
 * @author Zhanghh
 * @date 2019/5/14
 */
package com.nebula.mooc.webserver.util;

import com.nebula.mooc.core.entity.Score;
import com.nebula.mooc.webserver.dao.ScoreDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;

@Component
public class TaskUtil {

    @Autowired
    private ScoreDao scoreDao;

    @Autowired
    private ThreadPoolTaskExecutor threadPoolTaskExecutor;

    private enum DaoType {
        INSERT, UPDATE
    }

    private class ScoreTask implements Runnable {

        private Score score;
        private DaoType daoType;

        /**
         * 创建新任务
         *
         * @param score   参数值
         * @param daoType 类型，1 -> 插入，2 -> 更新
         */
        ScoreTask(Score score, DaoType daoType) {
            this.score = score;
            this.daoType = daoType;
        }

        @Override
        public void run() {
            switch (daoType) {
                case INSERT:
                    scoreDao.insertScore(score);
                    break;
                case UPDATE:
                    scoreDao.updateScore(score);
                    break;
            }
        }
    }

    /**
     * 插入评分表
     *
     * @param score 参数
     */
    public void insertScore(Score score) {
        ScoreTask scoreTask = new ScoreTask(score, DaoType.INSERT);
        threadPoolTaskExecutor.submit(scoreTask);
    }

    /**
     * 修改评分表
     *
     * @param score 参数
     */
    public void updateScore(Score score) {
        ScoreTask scoreTask = new ScoreTask(score, DaoType.UPDATE);
        threadPoolTaskExecutor.submit(scoreTask);
    }

    @PreDestroy
    public void destroy() {
        threadPoolTaskExecutor.shutdown();
    }

}
