/*
 * @author Zhanghh
 * @date 2019-05-14
 */
package com.nebula.mooc.webserver.config;

import com.nebula.mooc.core.entity.Score;
import com.nebula.mooc.webserver.dao.ScoreDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * 用于插入数据库，不占用控制器时间
 */
@Configuration
public class TaskConfig {

    @Autowired
    private ScoreDao scoreDao;

    @Autowired
    private ThreadPoolTaskExecutor threadPoolTaskExecutor;

    @Bean
    public ThreadPoolTaskExecutor threadPoolTaskExecutor() {
        ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
        threadPoolTaskExecutor.setDaemon(true);
        threadPoolTaskExecutor.setBeanName("TaskExecutor");
        // CallerRuns策略，队列已满时调用者线程执行
        threadPoolTaskExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        threadPoolTaskExecutor.initialize();
        return threadPoolTaskExecutor;
    }

    public void submit(Runnable task) {
        threadPoolTaskExecutor.submit(task);
    }

    public enum DaoType {
        INSERT, UPDATE
    }

    public class ScoreTask implements Runnable {

        private Score score;
        private DaoType daoType;

        /**
         * 创建新任务
         *
         * @param score   参数值
         * @param daoType 类型，1 -> 插入，2 -> 更新
         */
        public ScoreTask(Score score, DaoType daoType) {
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
                default:
                    throw new IllegalArgumentException("DaoType error.");
            }
        }
    }

}
