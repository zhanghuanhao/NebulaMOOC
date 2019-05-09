/*
 * @author Zhanghh
 * @date 2019/5/8
 */
package com.nebula.mooc.webserver.config;

import com.aliyun.oss.ClientConfiguration;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.common.auth.CredentialsProvider;
import com.aliyun.oss.common.auth.DefaultCredentialProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OssConfig {

    @Value("${oss.endpoint}")
    private String endpoint;

    @Value("${oss.accessKeyId}")
    private String accessKeyId;

    @Value("${oss.accessKeySecret}")
    private String accessKeySecret;

    @Value("${oss.headBucket}")
    private String headBucket;

    @Value("${oss.videoBucket}")
    private String videoBucket;

    @Bean
    OSSClient ossClient() {
        CredentialsProvider credentialsProvider = new DefaultCredentialProvider(accessKeyId, accessKeySecret);
        ClientConfiguration config = new ClientConfiguration();
        // 设置HTTP最大连接数
        config.setMaxConnections(1024);
        // 设置TCP连接超时
        config.setConnectionTimeout(5000);
        // 设置Socket传输数据超时
        config.setSocketTimeout(3000);
        return new OSSClient(endpoint, credentialsProvider, config);
    }

}
