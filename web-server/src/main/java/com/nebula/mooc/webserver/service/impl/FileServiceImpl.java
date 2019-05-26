/*
 * @author Zhanghh
 * @date 2019/5/8
 */
package com.nebula.mooc.webserver.service.impl;

import com.nebula.mooc.core.entity.Video;
import com.nebula.mooc.core.util.TokenUtil;
import com.nebula.mooc.webserver.dao.VideoDao;
import com.nebula.mooc.webserver.service.FileService;
import com.nebula.mooc.webserver.util.OssUtil;
import com.nebula.mooc.webserver.util.TaskUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service("FileService")
public class FileServiceImpl implements FileService {

    private static final Logger logger = LoggerFactory.getLogger(FileService.class);

    @Autowired
    private OssUtil ossUtil;

    @Autowired
    private VideoDao videoDao;

    @Autowired
    private TaskUtil taskUtil;

    public String uploadHead(MultipartFile file) {
        // 生成文件名
        String fileName = TokenUtil.generateName();
        // 上传文件
        try {
            if (ossUtil.uploadHead(fileName, file.getInputStream())) {
                return fileName;
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }

    public boolean uploadVideo(long userId, MultipartFile file) {
        String key = TokenUtil.generateName();
        Video video = new Video();
        video.setUserId(userId);
        video.setFilename(file.getOriginalFilename());
        video.setVideoUrl(key + ".mp4");
        try {
            if (videoDao.addVideo(video) == 1) {
                return taskUtil.uploadVideo(video, file);
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return false;
    }

    public List getVideoList(long userId) {
        return videoDao.getVideoList(userId);
    }

}
