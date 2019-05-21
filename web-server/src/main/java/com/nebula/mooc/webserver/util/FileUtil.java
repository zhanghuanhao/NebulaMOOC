/*
 * @author Zhanghh
 * @date 2019/5/9
 */
package com.nebula.mooc.webserver.util;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.event.ProgressEvent;
import com.aliyun.oss.event.ProgressEventType;
import com.aliyun.oss.event.ProgressListener;
import com.aliyun.oss.model.PutObjectRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;
import java.io.InputStream;
import java.util.concurrent.Future;

@Component
public class FileUtil {

    private static final Logger logger = LoggerFactory.getLogger(FileUtil.class);

    /**
     * 传输过程监听
     */
    private class ProgressListenerImpl implements ProgressListener {

        private boolean success = false;
        private boolean progressing = false;
        private String fileName;

        ProgressListenerImpl(String key) {
            fileName = key;
        }

        @Override
        public void progressChanged(ProgressEvent progressEvent) {
            ProgressEventType eventType = progressEvent.getEventType();
            switch (eventType) {
                // 传输开始
                case TRANSFER_STARTED_EVENT:
                    progressing = true;
                    logger.info("Upload start [{}].", fileName);
                    break;
                // 传输完成
                case TRANSFER_COMPLETED_EVENT:
                    success = true;
                    progressing = false;
                    logger.info("Upload success [{}].", fileName);
                    break;
                // 传输失败
                case TRANSFER_FAILED_EVENT:
                    progressing = false;
                    logger.info("Upload fail [{}].", fileName);
                    break;
            }
        }

        public boolean isSuccess() {
            return this.success;
        }

        public boolean isProgressing() {
            return this.progressing;
        }
    }

    @Autowired
    private OSSClient ossClient;

    @Autowired
    private TaskUtil taskUtil;

    @Value("${oss.headBucket}")
    private String headBucket;

    @Value("${oss.videoBucket}")
    private String videoBucket;

    public boolean uploadHead(String key, InputStream inputStream) {
        PutObjectRequest putObjectRequest = new PutObjectRequest(headBucket, key, inputStream);
        ProgressListenerImpl progressListener = new ProgressListenerImpl(key);
        putObjectRequest.setProgressListener(progressListener);
        ossClient.putObject(putObjectRequest);
        return progressListener.isSuccess();
    }

//    public boolean uploadVideo(String key, InputStream inputStream) {
//        PutObjectRequest putObjectRequest = new PutObjectRequest(videoBucket, key, inputStream);
//        ProgressListenerImpl progressListener = new ProgressListenerImpl(key);
//        putObjectRequest.setProgressListener(progressListener);
//        ossClient.putObject(putObjectRequest);
//        return progressListener.isSuccess();
//    }

    public void deleteHead(String key) {
        ossClient.deleteObject(headBucket, key);
    }

    public void deleteVideo(String key) {
        ossClient.deleteObject(videoBucket, key);
    }

    public Future deleteFileWithDelay(String fileName) {
        return taskUtil.deleteFileWithDelay(fileName);
    }

    @PreDestroy
    public void destroy() {
        ossClient.shutdown();
    }
}
