/*
 * @author Zhanghh
 * @date 2019/5/8
 */
package com.nebula.mooc.webserver.config;

import com.aliyun.oss.ClientConfiguration;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.common.auth.CredentialsProvider;
import com.aliyun.oss.common.auth.DefaultCredentialProvider;
import com.aliyun.oss.common.comm.Protocol;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OssConfig {

    private static final Logger logger = LoggerFactory.getLogger(OssConfig.class);

    @Value("${oss.socketTimeout}")
    private int socketTimeout;

    @Value("${oss.connectionTimeout}")
    private int connectionTimeout;

    @Value("${oss.idleConnectionTime}")
    private int idleConnectionTime;

    @Value("${oss.maxErrorRetry}")
    private int maxErrorRetry;

    @Value("${oss.https}")
    private boolean https;

    @Value("${oss.endpoint}")
    private String endpoint;

    @Value("${oss.accessKeyId}")
    private String accessKeyId;

    @Value("${oss.accessKeySecret}")
    private String accessKeySecret;

    @Bean
    OSSClient ossClient() {
        CredentialsProvider credentialsProvider = new DefaultCredentialProvider(accessKeyId, accessKeySecret);
        ClientConfiguration conf = new ClientConfiguration();
        conf.setSocketTimeout(socketTimeout);
        conf.setConnectionTimeout(connectionTimeout);
        conf.setIdleConnectionTime(idleConnectionTime);
        conf.setMaxErrorRetry(maxErrorRetry);
        if (https) conf.setProtocol(Protocol.HTTPS);
        else conf.setProtocol(Protocol.HTTP);
        OSSClient ossClient = new OSSClient(endpoint, credentialsProvider, conf);
        logger.info("OssClient inited.");
        return ossClient;
    }

}
