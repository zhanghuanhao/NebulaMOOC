/*
 * @author Zhanghh
 * @date 2019/5/8
 */
package com.nebula.mooc.webserver.service.impl;

import com.nebula.mooc.core.entity.UserInfo;
import com.nebula.mooc.core.entity.Video;
import com.nebula.mooc.core.util.TokenUtil;
import com.nebula.mooc.webserver.dao.FileDao;
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

    private static final String defaultHead = "default";

    @Autowired
    private OssUtil ossUtil;

    @Autowired
    private FileDao fileDao;

    @Autowired
    private TaskUtil taskUtil;

    public boolean uploadHead(UserInfo userInfo, MultipartFile file) {
        // 生成文件名
        String fileName = TokenUtil.generateName(userInfo);
        boolean result;
        try {
            // 上传文件
            result = ossUtil.uploadHead(fileName, file.getInputStream());
            if (result) {
                // 获取旧的头像地址
                String headUrl = fileDao.getHeadUrl(userInfo.getId());
                userInfo.setHeadUrl(fileName);
                result = fileDao.updateHeadUrl(userInfo) > 0;
                // 上传成功更新url
                if (result) {
                    // 如果原来的url是默认的，则不删除图片
                    if (!defaultHead.equals(headUrl))
                        ossUtil.deleteHead(headUrl);
                } else
                    userInfo.setHeadUrl(fileName);
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            result = false;
        }
        return result;
    }

    public boolean uploadVideo(UserInfo userInfo, MultipartFile file) {
        try {
            String key = TokenUtil.generateName(userInfo);
            Video video = new Video();
            video.setUserId(userInfo.getId());
            video.setFilename(file.getOriginalFilename());
            video.setUrl(key);
            if (fileDao.addVideo(video) == 1) {
                taskUtil.uploadVideo(video, file.getInputStream());
                return true;
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return false;
    }

    public List getVideoList(long userId) {
        return fileDao.getVideoList(userId);
    }

}
