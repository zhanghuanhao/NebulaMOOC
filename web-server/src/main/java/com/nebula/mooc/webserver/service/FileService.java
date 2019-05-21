/*
 * @author Zhanghh
 * @date 2019/5/8
 */
package com.nebula.mooc.webserver.service;

import com.nebula.mooc.core.entity.UserInfo;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface FileService {

    /**
     * 上传头像
     */
    boolean uploadHead(UserInfo userInfo, MultipartFile file);

    /**
     * 上传视频
     *
     * @return String 成功返回文件名
     */
    boolean uploadVideo(UserInfo userInfo, MultipartFile file);

    /**
     * 获取用户上传的视频列表
     */
    List getVideoList(long userId);
}
