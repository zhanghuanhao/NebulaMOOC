/*
 * @author Zhanghh
 * @date 2019/5/8
 */
package com.nebula.mooc.webserver.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface FileService {

    /**
     * 上传头像
     *
     * @param fileExtName 文件扩展名
     * @return 成功返回文件名，失败返回null
     */
    String uploadHead(MultipartFile file, String fileExtName);

    /**
     * 上传视频
     */
    boolean uploadVideo(long userId, MultipartFile file);

    /**
     * 获取用户上传的视频列表
     */
    List getVideoList(long userId);
}
