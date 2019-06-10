package com.nebula.mooc.webserver.util;

import org.csource.fastdfs.StorageClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

@Component
public class FastDFSUtil {

    private static final Logger logger = LoggerFactory.getLogger(FastDFSUtil.class);

    @Autowired
    private StorageClient imageClient;

    @Autowired
    private StorageClient videoClient;

    /**
     * 上传头像
     *
     * @param file        文件对象流
     * @param fileExtName 文件扩展名
     * @return 生成的文件路径
     */
    public String uploadHead(MultipartFile file, String fileExtName) {
        long time = System.currentTimeMillis();
        String[] result = null;
        try {
            byte[] file_buff;
            InputStream inputStream = file.getInputStream();
            int len = inputStream.available();
            file_buff = new byte[len];
            inputStream.read(file_buff);
            result = imageClient.upload_file(file_buff, fileExtName, null);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        time = System.currentTimeMillis() - time;
        logger.info("Upload head: {} Cost: {}ms", file.getOriginalFilename(), time);
        if (result != null)
            return result[1];
        return null;
    }

    /**
     * 上传视频
     *
     * @param file 文件对象流
     * @return 生成的文件路径
     */
    public String uploadVideo(MultipartFile file) {
        long time = System.currentTimeMillis();
        String[] result = null;
        try {
            byte[] file_buff;
            InputStream inputStream = file.getInputStream();
            int len = inputStream.available();
            file_buff = new byte[len];
            inputStream.read(file_buff);
            result = videoClient.upload_file(file_buff, "mp4", null);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        time = System.currentTimeMillis() - time;
        logger.info("Upload video: {} Cost: {}ms", file.getOriginalFilename(), time);
        if (result != null)
            return result[1];
        return null;
    }

}