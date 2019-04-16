package com.nebula.mooc.core.util;

public class MinioService {

//    private static final Logger LOGGER = LogManager.getLogger(MinioService.class);
//
//    private MinioConfig minioConfig;
//
//    private MinioClient minioClient;
//
//    @PostConstruct
//    public void init() {
//        try {
//            // Create a minioClient with the Minio Server name, Port, Access key and Secret key.
//            minioClient = new MinioClient(minioConfig.getHttp(),
//                    minioConfig.getAccount(), minioConfig.getPassword());
//        } catch (Exception e) {
//            LOGGER.error(e.getMessage(), e);
//        }
//    }
//
//    private void makeBucket(String bucketName) throws Exception {
//        // Check if the bucket already exists.
//        boolean isExist = minioClient.bucketExists(bucketName);
//        if (isExist) {
//            LOGGER.info("Bucket already exists.");
//        } else {
//            // Make a new bucket
//            minioClient.makeBucket(bucketName);
//        }
//    }
//
//    public boolean pushObject(String bucketName,
//                              String fullFileName, String targetFileFullName) {
//        try {
//            makeBucket(bucketName);
//            minioClient.putObject(bucketName, fullFileName, targetFileFullName);
//            return true;
//        } catch (Exception e) {
//            LOGGER.error(e.getMessage(), e);
//            return false;
//        }
//    }
//
//    public String getObjectUrl(String bucket, String filename) {
//        //有效浏览一天
//        try {
//            String objectUrl = minioClient.presignedGetObject(bucket, filename, 60 * 60 * 24);
//            return objectUrl;
//        } catch (InvalidBucketNameException | NoSuchAlgorithmException | InsufficientDataException |
//                IOException | InvalidKeyException | NoResponseException |
//                XmlPullParserException | ErrorResponseException | InvalidExpiresRangeException |
//                InternalException e) {
//            LOGGER.error(e.getMessage(), e);
//            return null;
//        }
//
//    }
//
//    public boolean copyObject(String bucketName,
//                              String filename, String targetBucketName, String targetFilename) {
//        try {
//            makeBucket(targetBucketName);
//            //Copy file from old bucket to the new one
//            minioClient.copyObject(bucketName, filename, targetBucketName, targetFilename);
//            return true;
//        } catch (Exception e) {
//            LOGGER.error(e.getMessage(), e);
//            return false;
//        }
//    }
//
//    public void removeObject(String bucketName, String filename) {
//        try {
//            //Doesn't care about whether delete is successful
//            minioClient.removeObject(bucketName, filename);
//        } catch (Exception e) {
//        }
//    }
//
//    public MinioConfig getMinioConfig() {
//        return minioConfig;
//    }
//
//    public void setMinioConfig(MinioConfig minioConfig) {
//        this.minioConfig = minioConfig;
//    }
}
