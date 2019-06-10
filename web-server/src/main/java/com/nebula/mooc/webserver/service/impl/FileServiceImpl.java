/*
 * @author Zhanghh
 * @date 2019/5/8
 */
package com.nebula.mooc.webserver.service.impl;

import com.nebula.mooc.core.entity.Video;
import com.nebula.mooc.webserver.dao.VideoDao;
import com.nebula.mooc.webserver.service.FileService;
import com.nebula.mooc.webserver.util.FastDFSUtil;
import com.nebula.mooc.webserver.util.TaskUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service("FileService")
public class FileServiceImpl implements FileService {

    @Autowired
    private FastDFSUtil fastDFSUtil;

    @Autowired
    private VideoDao videoDao;

    @Autowired
    private TaskUtil taskUtil;

    public String uploadHead(MultipartFile file, String fileExtName) {
        // 上传文件
        return fastDFSUtil.uploadHead(file, fileExtName);
    }

    public boolean uploadVideo(long userId, MultipartFile file) {
        Video video = new Video();
        video.setUserId(userId);
        video.setFilename(file.getOriginalFilename());
            if (videoDao.addVideo(video) == 1) {
                return taskUtil.uploadVideo(video, file);
            }
        return false;
    }

    public List getVideoList(long userId) {
        return videoDao.getVideoList(userId);
    }

}
