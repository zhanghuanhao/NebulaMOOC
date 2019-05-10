/*
 * @author Zhanghh
 * @date 2019/5/8
 */
package com.nebula.mooc.webserver.service;

import com.nebula.mooc.core.entity.UserInfo;
import org.springframework.web.multipart.MultipartFile;

public interface FileService {

    /**
     * 上传头像
     */
    boolean uploadHead(UserInfo userInfo, MultipartFile file);

    /**
     * 上传视频
     */
    boolean uploadVideo(UserInfo userInfo, MultipartFile file);
}
