/*
 * @author Zhanghh
 * @date 2019/5/8
 */
package com.nebula.mooc.webserver.service.impl;

import com.nebula.mooc.core.entity.UserInfo;
import com.nebula.mooc.core.entity.Video;
import com.nebula.mooc.core.util.TokenUtil;
import com.nebula.mooc.webserver.dao.VideoDao;
import com.nebula.mooc.webserver.service.VideoService;
import com.nebula.mooc.webserver.util.OssUtil;
import com.nebula.mooc.webserver.util.TaskUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service("FileService")
public class VideoServiceImpl implements VideoService {

    private static final Logger logger = LoggerFactory.getLogger(VideoService.class);

    @Autowired
    private OssUtil ossUtil;

    @Autowired
    private VideoDao videoDao;

    @Autowired
    private TaskUtil taskUtil;

    public boolean uploadHead(UserInfo userInfo, MultipartFile file) {
        // 生成文件名
        String fileName = TokenUtil.generateName(userInfo);
        try {
            // 上传文件
            if (ossUtil.uploadHead(fileName, file.getInputStream())) {
                // 上传成功更新url
                userInfo.setHeadUrl(fileName);
                return true;
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return false;
    }

    public boolean uploadVideo(UserInfo userInfo, MultipartFile file) {
        try {
            String key = TokenUtil.generateName(userInfo);
            Video video = new Video();
            video.setUserId(userInfo.getId());
            video.setFilename(file.getOriginalFilename());
            video.setUrl(key);
            if (videoDao.addVideo(video) == 1) {
                taskUtil.uploadVideo(video, file.getInputStream());
                return true;
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
