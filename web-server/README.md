# 网页服务器模块

用于处理网络请求的模块，包括主页、课程、讨论区。  
默认开启端口为80、443，所有访问80的端口会被重定向到443（https）端口。  
该模块设计为分布式，可通过多台服务器部署后配置Nginx负载均衡。  

# 包结构

- core                           -> 核心模块
- spring-boot-starter-web        -> Spring Web
- mybatis-spring-boot-starter    -> MyBatis 持久化框架
- mysql-connector-java           -> MySql连接驱动
- jsoup                          -> 用于防御xss攻击，过滤html和js
- spring-boot-starter-mail       -> 提供邮件服务
- commons-pool2                  -> 提供lettuce pool 缓存连接池
- spring-boot-starter-data-redis -> 用于缓存用户登陆后的信息

# 目录结构

```
src
└── main
    ├── java
    │   └── com.nebula.mooc.webserver
    │                       ├── WebServer.java  启动类
    │                       ├── config
    │                       │   ├── BasicConfig.java    Spring Web的一些配置
    │                       │   ├── ControllerLog.java  控制器记录配置
    │                       │   ├── FastDFSConfig.java  FastDFS配置
    │                       │   ├── GRpcConfig.java     GRPC配置
    │                       │   ├── RedisConfig.java    Redis配置，用于缓存一些控制器的返回值
    │                       │   ├── ServiceLog.java     服务记录配置
    │                       │   ├── SslConfig.java      SSL配置
    │                       │   └── TaskConfig.java     线程池配置
    │                       ├── controller
    │                       │   ├── CodeController.java         验证码控制器
    │                       │   ├── CourseOpController.java     课程操作控制器
    │                       │   ├── CourseQueryController.java  课程查询控制器
    │                       │   ├── ErrorHandler.java           错误处理控制器
    │                       │   ├── PostOpController.java       帖子操作控制器
    │                       │   ├── PostQueryController.java    帖子查询控制器
    │                       │   ├── UserController.java         用户操作控制器
    │                       │   ├── UserInfoController.java     用户信息控制器
    │                       │   └── VideoController.java        视频处理控制
    │                       ├── dao
    │                       │   ├── CourseDao.java  课程处理接口
    │                       │   ├── PostDao.java    帖子处理接口
    │                       │   ├── ScoreDao.java   积分处理接口
    │                       │   └── VideoDao.java   视频处理接口
    │                       ├── interceptor
    │                       │   ├── LoginInterceptor.java   登陆拦截器，检测是否登陆
    │                       │   └── XssInterceptor.java     Xss过滤器，过滤参数中的XSS字段
    │                       ├── service
    │                       │   ├── CodeService.java    验证码服务
    │                       │   ├── CourseService.java  课程服务
    │                       │   ├── FileService.java    文件服务
    │                       │   ├── PostService.java    帖子服务
    │                       │   ├── ScoreService.java   积分服务
    │                       │   ├── UserService.java    用户服务
    │                       │   └── impl    服务实现
    │                       └── util
    │                           ├── CacheUtil.java      获取session和cookie中的信息
    │                           ├── CodeUtil.java       验证码工具
    │                           ├── FastDFSUtil.java    FastDFS文件上传处理工具
    │                           ├── FileUtil.java       文件工具
    │                           ├── MailUtil.java       邮件工具
    │                           ├── RecommendUtil.java  推荐服务工具
    │                           └── TaskUtil.java       异步执行上传工作的线程池工具
    └── resources
        ├── application.yml     Mail服务、Redis数据库选择、端口配置、SSL、FastDFS、线程池配置等
        ├── mapper
        │   ├── CourseMapper.xml    课程数据层语句
        │   ├── PostMapper.xml      帖子数据层语句
        │   ├── ScoreMapper.xml     分数数据层语句
        │   └── VideoMapper.xml     视频数据层语句
        ├── nebulamooc.pfx  配置https的证书
        └── static  静态网页文件夹
```
