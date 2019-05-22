/*
 * @author Zhanghh
 * @date 2019/5/8
 */
package com.nebula.mooc.webserver.service.impl;

import com.nebula.mooc.core.entity.UserInfo;
import com.nebula.mooc.core.entity.Video;
import com.nebula.mooc.core.util.TokenUtil;
import com.nebula.mooc.webserver.dao.VideoDao;
import com.nebula.mooc.webserver.service.FileService;
import com.nebula.mooc.webserver.util.OssUtil;
import com.nebula.mooc.webserver.util.TaskUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service("FileService")
public class FileServiceImpl implements FileService {

    @Autowired
    private OssUtil ossUtil;

    @Autowired
    private VideoDao videoDao;

    @Autowired
    private TaskUtil taskUtil;

    public boolean uploadHead(UserInfo userInfo, MultipartFile file) {
        // 生成文件名
        String fileName = TokenUtil.generateName(userInfo);
        // 上传文件
        if (ossUtil.uploadHead(fileName, file)) {
            // 上传成功更新url
            userInfo.setHeadUrl(fileName);
            return true;
        }
        return false;
    }

    public boolean uploadVideo(UserInfo userInfo, MultipartFile file) {
        String key = TokenUtil.generateName(userInfo);
        Video video = new Video();
        video.setUserId(userInfo.getId());
        video.setFilename(file.getOriginalFilename());
        video.setUrl(key + ".mp4");
        if (videoDao.addVideo(video) == 1) {
            taskUtil.uploadVideo(video, file);
            return true;
        }
        return false;
    }

    public List getVideoList(long userId) {
        return videoDao.getVideoList(userId);
    }

}
