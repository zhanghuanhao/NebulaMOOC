/*
 * @author Zhanghh
 * @date 2019/4/4
 */
package com.nebula.mooc.liveserver.config;

import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslContextBuilder;
import org.apache.catalina.connector.Connector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

/*
 * 配置SSL
 */
@Configuration
public class SslConfig {

    private static final Logger logger = LoggerFactory.getLogger(SslConfig.class);

    /**
     * 证书位置
     */
    @Value("classpath:${ssl.certpath}")
    private Resource certPath;

    /**
     * 私钥位置
     */
    @Value("classpath:${ssl.keypath}")
    private Resource keyPath;

    /**
     * 设置Nginx RTMP回调端口
     */
    @Value("${port.http}")
    private int callbackPort;

    /**
     * 设置外网https端口
     */
    @Value("${port.https}")
    private int httpsPort;

    /**
     * 开放回调端口和外网ssl端口并存
     */
    private Connector httpConnector() {
        Connector connector = new Connector();
        //Connector监听的callback端口号
        connector.setPort(callbackPort);
        logger.info("Set callback port: {}", callbackPort);
        return connector;
    }

    @Bean
    public SslContext sslContext() throws Exception {
        // 构建sslContext
        return SslContextBuilder
                // 使用File类型在打包jar里无法读取
                .forServer(certPath.getInputStream(), keyPath.getInputStream())
                .build();
    }

    @Bean
    public ServletWebServerFactory servletContainer() {
        TomcatServletWebServerFactory tomcat = new TomcatServletWebServerFactory();
        tomcat.addAdditionalTomcatConnectors(this.httpConnector());
        logger.info("SSL support inited.");
        return tomcat;
    }

}
