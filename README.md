# NebulaMOOC

**一个基于B/S架构及分布式的在线慕课平台，包括了课程、讨论区、直播三大模块**

项目整体基于SprintBoot 2.1.5，模块间的通信基于**Protobuf**作为通信协议、**GRPC**作为远程调用服务，使用Spring AOP和log4j2作为日志系统，包括实现了基于Redis缓存的单点登录系统，基于Netty、Protobuf、WebSocket、Nginx-with-rtmp的直播服务器，基于Redis缓存、Spring Web的网页服务器。  

下面为整体架构图：

![服务器架构图](./extra_img/struct.jpg)

Nginx RTMP服务器架构图：

![Nginx RTMP服务器](./extra_img/rtmp_struct.png)

FastDFS文件服务器架构图：

![FastDFS文件服务器](./extra_img/fastdfs_struct.png)
