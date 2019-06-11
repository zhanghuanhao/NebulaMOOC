/*
 * @author Zhanghh
 * @date 2019/6/10
 */
package com.nebula.mooc.webserver.config;

import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerGroup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.InetSocketAddress;

@Configuration
public class FastDFSConfig {

    private static final Logger logger = LoggerFactory.getLogger(FastDFSConfig.class);

    @Value("${fdfs.connectTimeout}")
    private int connectTimeout;

    @Value("${fdfs.networkTimeout}")
    private int networkTimeout;

    @Value("${fdfs.charset}")
    private String charset;

    @Value("${fdfs.trackerServerAddress}")
    private String trackerServerAddress;

    private TrackerGroup getTrackerGroup() throws Exception {
        String[] trackerServers = trackerServerAddress.split(",");
        InetSocketAddress[] tracker_servers = new InetSocketAddress[trackerServers.length];
        for (int i = 0; i < trackerServers.length; i++) {
            String[] parts = trackerServers[i].split(":", 2);
            if (parts.length != 2) {
                throw new Exception("the value of item \"trackerServerAddress\" is invalid, the correct format is host:port");
            }
            tracker_servers[i] = new InetSocketAddress(parts[0].trim(), Integer.parseInt(parts[1].trim()));
        }
        return new TrackerGroup(tracker_servers);
    }

    @Bean
    public TrackerClient trackerClient() {
        try {
            ClientGlobal.setG_connect_timeout(connectTimeout);
            ClientGlobal.setG_network_timeout(networkTimeout);
            ClientGlobal.setG_charset(charset);
            ClientGlobal.setG_tracker_group(getTrackerGroup());
            TrackerClient trackerClient = new TrackerClient(ClientGlobal.g_tracker_group);
            logger.info("FastDFS - TrackerClient inited");
            return trackerClient;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return null;
        }
    }

}
