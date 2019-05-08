/*
 * @author Zhanghh
 * @date 2019/4/11
 */
package com.nebula.mooc.webserver.util;

import io.minio.MinioClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class MinioUtil {

    @Value("${minio.username}")
    private String username;

    @Value("${minio.password}")
    private String password;

    @Value("${minio.address}")
    private String address;

    private MinioClient minioClient;

    @PostConstruct
    public void init() throws Exception {
        // Create a minioClient with the Minio Server name, Port, Access key and Secret key.
        minioClient = new MinioClient(address, username, password);
    }

    private void makeBucket(String bucketName) throws Exception {
        // Check if the bucket already exists.
        if (!minioClient.bucketExists(bucketName)) {
            // Make a new bucket
            minioClient.makeBucket(bucketName);
        }
    }

    public void pushObject(String bucketName, String fullFileName,
                           String targetFileFullName) throws Exception {
        makeBucket(bucketName);
        minioClient.putObject(bucketName, fullFileName, targetFileFullName);
    }

    public String getObjectUrl(String bucket, String filename) throws Exception {
        //有效浏览一天
        String objectUrl = minioClient.presignedGetObject(bucket, filename, 60 * 60 * 24);
        return objectUrl;
    }

    public void copyObject(String bucketName, String filename,
                           String targetBucketName, String targetFilename) throws Exception {
        makeBucket(targetBucketName);
        //Copy file from old bucket to the new one
        minioClient.copyObject(bucketName, filename, targetBucketName, targetFilename);
    }

    public void removeObject(String bucketName, String filename) throws Exception {
        //Doesn't care about whether delete is successful
        minioClient.removeObject(bucketName, filename);
    }

}
