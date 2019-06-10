/*
 * @author Zhanghh
 * @date 2019/6/10
 */
package com.nebula.mooc.webserver.config;

import org.csource.fastdfs.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FastDFSConfig {

    private static final Logger logger = LoggerFactory.getLogger(FastDFSConfig.class);

    @Autowired
    private TrackerServer trackerServer;

    @Bean
    public TrackerServer trackerServer() {
        TrackerServer trackerServer = null;
        try {
            ClientGlobal.init("fdfs_client.conf");
            TrackerClient trackerClient = new TrackerClient(ClientGlobal.g_tracker_group);
            trackerServer = trackerClient.getConnection();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        logger.info("FastDFS - Tracker inited");
        return trackerServer;
    }

    @Bean
    public StorageClient imageClient() {
        StorageClient imageClient = null;
        try {
            TrackerClient trackerClient = new TrackerClient(ClientGlobal.g_tracker_group);
            StorageServer storageServer = trackerClient.getStoreStorage(trackerServer, "image");
            imageClient = new StorageClient(trackerServer, storageServer);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        logger.info("FastDFS - Image Storage inited");
        return imageClient;
    }

    @Bean
    public StorageClient videoClient() {
        StorageClient videoClient = null;
        try {
            TrackerClient trackerClient = new TrackerClient(ClientGlobal.g_tracker_group);
            StorageServer storageServer = trackerClient.getStoreStorage(trackerServer, "video");
            videoClient = new StorageClient(trackerServer, storageServer);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        logger.info("FastDFS - Video Storage inited");
        return videoClient;
    }

}
