/*
 * @author Zhanghh
 * @date 2019/5/8
 */
package com.nebula.mooc.webserver.service.impl;

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

@Service("FileService")
public class FileServiceImpl implements FileService {

    private static final Logger logger = LoggerFactory.getLogger(FileService.class);

    @Autowired
    private FileUtil fileUtil;

    @Autowired
    private FileDao fileDao;

    public boolean uploadHead(UserInfo userInfo, MultipartFile file) {
        String fileName = file.getOriginalFilename();
        fileName = TokenUtil.generateFileName(userInfo, fileName);
        try {
            fileUtil.uploadHead(fileName, file.getInputStream());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return false;
        }
        return true;
    }

    public boolean uploadVideo(UserInfo userInfo, MultipartFile file) {
        return true;
    }
}
