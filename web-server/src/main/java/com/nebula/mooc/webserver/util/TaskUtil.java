/*
 * @author Zhanghh
 * @date 2019/5/14
 */
package com.nebula.mooc.webserver.util;

import com.nebula.mooc.core.entity.CourseScore;
import com.nebula.mooc.core.entity.PostScore;
import com.nebula.mooc.core.entity.Video;
import com.nebula.mooc.webserver.dao.ScoreDao;
import com.nebula.mooc.webserver.dao.VideoDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PreDestroy;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

@Component
public class TaskUtil {

    @Autowired
    private OssUtil ossUtil;

    @Autowired
    private ThreadPoolTaskScheduler scheduler;

    @Autowired
    private ScoreDao scoreDao;

    @Autowired
    private VideoDao videoDao;

    /**
     * 修改课程评分表
     *
     * @param courseScore 参数
     */
    public void updateCourseScore(CourseScore courseScore) {
        scheduler.submit(() -> {
            int result = scoreDao.updateCourseScore(courseScore);
            if (result == 0) {
                // 表中无数据，则插入
                scoreDao.insertCourseScore(courseScore);
            }
        });
    }

    /**
     * 修改讨论区评分表
     *
     * @param postScore 参数
     */
    public void updatePostScore(PostScore postScore) {
        scheduler.submit(() -> {
            int result = scoreDao.updatePostScore(postScore);
            if (result == 0) {
                // 表中无数据，则插入
                scoreDao.insertPostScore(postScore);
            }
        });
    }

    /**
     * 上传传输过来的视频
     *
     * @param video 参数
     * @param file  需上传的视频文件
     */
    public boolean uploadVideo(Video video, MultipartFile file) throws Exception {
        // 将文件转储到本地
        File newFile = FileUtil.transferTo(file, video.getVideoUrl());
        final InputStream inputStream = new FileInputStream(newFile);
        scheduler.submit(() -> {
            if (ossUtil.uploadVideo(video.getVideoUrl(), inputStream)) {
                videoDao.updateVideo(video);
                newFile.delete();
            } else
                videoDao.removeVideo(video);
        });
        return true;
    }

    @PreDestroy
    public void destroy() {
        scheduler.shutdown();
    }

}
