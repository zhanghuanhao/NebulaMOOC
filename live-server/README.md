# 直播模块

用于创建、查看、管理直播的模块，其中包括了Nginx回调、直播管理接口。  
Websocket默认路径为/websocket，端口是9080  
Nginx回调端口: 8080，直播管理请求端口: 8443（https）  
该模块为单机设计，不支持分布式部署  

# 包结构

- core                    -> 核心模块
- spring-boot-starter-web -> Spring Web
- jsoup                   -> 用于防御xss攻击，过滤html和js
- netty-all               -> 用于处理直播间聊天信息

# 目录结构

```
src
└── main
    ├── java
    │   └── com.nebula.mooc.liveserver
    │                       ├── LiveServer.java 启动类
    │                       ├── config
    │                       │   ├── BootstrapConfig.java        Netty启动配置和初始化 
    │                       │   ├── ControllerLog.java          控制器记录配置
    │                       │   ├── GRpcConfig.java             GRPC配置，用于用户服务
    │                       │   ├── HandlerLog.java             Netty Channel记录
    │                       │   ├── PipeLineConfig.java         Netty管道的配置
    │                       │   ├── SslChannelInitializer.java  在所有Channel头添加SSL管道
    │                       │   └── SslConfig.java              控制器SSL配置
    │                       ├── controller
    │                       │   ├── CallbackController.java Nginx-RTMP回调控制器
    │                       │   └── LiveController.java     直播控制器
    │                       ├── core
    │                       │   ├── ChatMessage.java    基于Protobuf生成的聊天信息
    │                       │   ├── LiveManager.java    直播管理器
    │                       │   └── Runner.java         在后台启动Netty坚听
    │                       ├── handler
    │                       │   ├── ByteToFrameHandler.java  Byte转Frame
    │                       │   ├── ChatHandler.java         聊天Channel
    │                       │   ├── FrameToByteHandler.java  Frame转Byte
    │                       │   └── HandshakeHandler.java    Websocket握手Channel
    │                       └── service
    │                           ├── LiveService.java    直播服务接口
    │                           ├── UserService.java    用户服务接口
    │                           └── impl 服务的实现
    └── resources
        ├── application.yml     WebSocket端口和路径、http和https端口、SSL配置
        ├── nebulamooc.key      用于Netty中的SSL通信
        ├── nebulamooc.pem      用于Netty中的SSL通信
        └── nebulamooc.pfx      用于建立HTTPS
```
