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
    private FastDFSUtil fastDFSUtil;

    @Autowired
    private ThreadPoolTaskScheduler scheduler;

    @Autowired
    private ScoreDao scoreDao;

    @Autowired
    private VideoDao videoDao;

    /**
     * 增加课程分数
     */
    public void incrCourse(CourseScore courseScore) {
        scheduler.submit(() -> scoreDao.incrCourse(courseScore));
    }

    /**
     * 减少课程分数
     */
    public void decrCourse(CourseScore courseScore) {
        scheduler.submit(() -> scoreDao.decrCourse(courseScore));
    }

    /**
     * 增加讨论区分数
     */
    public void incrPost(PostScore postScore) {
        scheduler.submit(() -> scoreDao.incrPost(postScore));
    }

    /**
     * 减少讨论区分数
     */
    public void decrPost(PostScore postScore) {
        scheduler.submit(() -> scoreDao.decrPost(postScore));
    }

    /**
     * 新增课程分数
     */
    public void viewCourse(CourseScore courseScore) {
        scheduler.submit(() -> {
            if (scoreDao.checkCourse(courseScore) == 0)
                scoreDao.viewCourse(courseScore);
        });
    }

    /**
     * 新增讨论区分数
     */
    public void viewPost(PostScore postScore) {
        scheduler.submit(() -> {
            if (scoreDao.checkPost(postScore) == 0)
                scoreDao.viewPost(postScore);
        });
    }

    /**
     * 上传传输过来的视频
     *
     * @param video 参数
     * @param file  需上传的视频文件
     */
    public boolean uploadVideo(Video video, MultipartFile file) {
        try {
            // 将file转储防止临时文件被删
            File newFile = FileUtil.transferTo(file);
            final InputStream inputStream = new FileInputStream(newFile);
            scheduler.submit(() -> {
                String fileName = fastDFSUtil.uploadVideo(inputStream, file.getOriginalFilename());
                if (fileName != null) {
                    video.setVideoUrl(fileName);
                    videoDao.updateVideo(video);
                } else
                    videoDao.removeVideo(video);
            });
        } catch (Exception e) {
        }
        return true;
    }

    @PreDestroy
    public void destroy() {
        scheduler.shutdown();
    }

}
