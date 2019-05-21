/*
 * @author Zhanghh
 * @date 2019/5/8
 */
package com.nebula.mooc.webserver.service.impl;

import com.nebula.mooc.core.Constant;
import com.nebula.mooc.core.entity.UserInfo;
import com.nebula.mooc.core.util.TokenUtil;
import com.nebula.mooc.webserver.dao.FileDao;
import com.nebula.mooc.webserver.service.FileService;
import com.nebula.mooc.webserver.util.FileUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;

@Service("FileService")
public class FileServiceImpl implements FileService {

    private static final Logger logger = LoggerFactory.getLogger(FileService.class);

    private static final String defaultHead = "default";

    @Autowired
    private FileUtil fileUtil;

    @Autowired
    private FileDao fileDao;

    public boolean uploadHead(UserInfo userInfo, MultipartFile file) {
        // 生成文件名
        String fileName = TokenUtil.generateName(userInfo);
        boolean result;
        try {
            // 上传文件
            result = fileUtil.uploadHead(fileName, file.getInputStream());
            if (result) {
                // 获取旧的头像地址
                String headUrl = fileDao.getHeadUrl(userInfo.getId());
                userInfo.setHeadUrl(fileName);
                result = fileDao.updateHeadUrl(userInfo) > 0;
                // 上传成功更新url
                if (result) {
                    // 如果原来的url是默认的，则不删除图片
                    if (!defaultHead.equals(headUrl))
                        fileUtil.deleteHead(headUrl);
                } else
                    userInfo.setHeadUrl(fileName);
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            result = false;
        }
        return result;
    }

    public String uploadVideo(UserInfo userInfo, MultipartFile file, HttpSession session) {
        // 生成文件名
        String fileName = Constant.TEMP_PATH + TokenUtil.generateName(userInfo);
        try {
            file.transferTo(new File(fileName));
            session.setAttribute(Constant.VIDEO, fileUtil.deleteFileWithDelay(fileName));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return fileName;
    }


}
