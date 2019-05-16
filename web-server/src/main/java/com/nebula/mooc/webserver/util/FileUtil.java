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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;
import java.io.InputStream;

@Component
public class FileUtil {

    /**
     * 传输过程监听
     */
    private class ProgressListenerImpl implements ProgressListener {

        private boolean success = false;

        @Override
        public void progressChanged(ProgressEvent progressEvent) {
            ProgressEventType eventType = progressEvent.getEventType();
            switch (eventType) {
                // 传输完成
                case TRANSFER_COMPLETED_EVENT:
                    this.success = true;
                    break;
                // 传输失败
                case TRANSFER_FAILED_EVENT:
                    break;
            }
        }

        public boolean isSuccess() {
            return this.success;
        }
    }

    @Autowired
    private OSSClient ossClient;

    @Value("${oss.headBucket}")
    private String headBucket;

    @Value("${oss.videoBucket}")
    private String videoBucket;

    public boolean uploadHead(String key, InputStream inputStream) {
        PutObjectRequest putObjectRequest = new PutObjectRequest(headBucket, key, inputStream);
        ProgressListenerImpl progressListener = new ProgressListenerImpl();
        putObjectRequest.setProgressListener(progressListener);
        ossClient.putObject(putObjectRequest);
        return progressListener.isSuccess();
    }

    public boolean uploadVideo(String key, InputStream inputStream) {
        return true;
    }

    public void deleteHead(String key) {
        ossClient.deleteObject(headBucket, key);
    }

    @PreDestroy
    public void destroy() {
        ossClient.shutdown();
    }
}
