/*
 * @author Zhanghh
 * @date 2019/5/9
 */
package com.nebula.mooc.webserver.util;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.PutObjectRequest;
import com.aliyun.oss.model.PutObjectResult;
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

    public void uploadHead(String key, InputStream inputStream) throws Exception {
        PutObjectRequest putObjectRequest = new PutObjectRequest(headBucket, key, inputStream);


        PutObjectResult putObjectResult = ossClient.putObject(putObjectRequest);
        System.out.println(putObjectResult);
// 数据读取完成后，获取的流必须关闭，否则会造成连接泄漏，导致请求无连接可用，程序无法正常工作。


    }

    @Override
    public void destroy() {
        ossClient.shutdown();
    }
}
