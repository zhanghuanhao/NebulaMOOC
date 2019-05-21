/*
 * @author Zhanghh
 * @date 2019/5/8
 */
package com.nebula.mooc.webserver.service;

import com.nebula.mooc.core.entity.UserInfo;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;

public interface FileService {

    /**
     * 上传头像
     */
    boolean uploadHead(UserInfo userInfo, MultipartFile file);

    /**
     * 上传视频
     *
     * @param session 存Future，用于取消删除任务
     * @return String 成功返回文件名
     */
    String uploadVideo(UserInfo userInfo, MultipartFile file, HttpSession session);
}
