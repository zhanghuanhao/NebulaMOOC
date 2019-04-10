/*
 * @author Zhanghh
 * @date 2019/4/4
 */
package com.nebula.mooc.webserver.config;

import org.apache.catalina.Context;
import org.apache.catalina.connector.Connector;
import org.apache.tomcat.util.descriptor.web.SecurityCollection;
import org.apache.tomcat.util.descriptor.web.SecurityConstraint;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/*
 * 注意不能放到WebConfig下，否则会报错
 */
@Configuration
public class SslConfig {

    private static final int httpPort = 80;
    private static final int httpsPort = 443;

    @Bean
    public ServletWebServerFactory servletContainer() {
        TomcatServletWebServerFactory tomcat = new TomcatServletWebServerFactory() {
            //表示对访问的上下文进行预处理
            @Override
            protected void postProcessContext(Context context) {
                SecurityConstraint securityConstraint = new SecurityConstraint();
                securityConstraint.setUserConstraint("confidential");    //机密的; 秘密的; 表示信任的;
                SecurityCollection collection = new SecurityCollection();
                collection.addPattern("/*");    //匹配根目录下的所有地址
                securityConstraint.addCollection(collection);
                context.addConstraint(securityConstraint);
            }
        };
        tomcat.addAdditionalTomcatConnectors(this.http2HttpsConnector());
        return tomcat;
    }

    @Bean
    public Connector http2HttpsConnector() {
        Connector connector = new Connector();
        //Connector监听的http的端口号
        connector.setPort(httpPort);
        //监听到http的端口号后转向到的https的端口号
        connector.setRedirectPort(httpsPort);
        return connector;
    }
}
