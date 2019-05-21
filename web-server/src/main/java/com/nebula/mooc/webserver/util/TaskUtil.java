/*
 * @author Zhanghh
 * @date 2019/5/14
 */
package com.nebula.mooc.webserver.util;

import com.nebula.mooc.core.entity.Score;
import com.nebula.mooc.webserver.dao.ScoreDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;
import java.io.File;
import java.util.concurrent.Future;

@Component
public class TaskUtil {

    @Autowired
    private ScoreDao scoreDao;

    @Autowired
    private ThreadPoolTaskExecutor threadPoolTaskExecutor;

    @Autowired
    private ThreadPoolTaskScheduler threadPoolTaskScheduler;

    private enum DaoType {
        INSERT, UPDATE
    }

    private class ScoreTask implements Runnable {

        private Score score;
        private DaoType daoType;

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

    private class FileDeleteTask implements Runnable {

        private String fileName;

        FileDeleteTask(String fileName) {
            this.fileName = fileName;
        }

        @Override
        public void run() {
            File file = new File(fileName);
            if (file.exists()) {
                file.delete();
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

    /**
     * 在30分钟后删除本地的文件
     *
     * @param fileName 文件名
     */
    public Future deleteFileWithDelay(String fileName) {
        FileDeleteTask fileDeleteTask = new FileDeleteTask(fileName);
        return threadPoolTaskScheduler.scheduleAtFixedRate(fileDeleteTask, 30 * 60);
    }

    @PreDestroy
    public void destroy() {
        threadPoolTaskExecutor.shutdown();
    }

}
