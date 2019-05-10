/*
 * @author Zhanghh
 * @date 2019/5/9
 */
package com.nebula.mooc.webserver.util;

import com.aliyun.oss.OSSClient;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.InputStream;

@Component
public class FileUtil implements DisposableBean {

    @Autowired
    private OSSClient ossClient;

    @Value("${oss.headBucket}")
    private String headBucket;

    @Value("${oss.videoBucket}")
    private String videoBucket;

    public void uploadHead(String key, InputStream inputStream) {
        ossClient.putObject(headBucket, key, inputStream);
    }

    public void uploadVideo(String key, InputStream inputStream) {
        ossClient.putObject(videoBucket, key, inputStream);
    }

    @Override
    public void destroy() {
        ossClient.shutdown();
    }
}
