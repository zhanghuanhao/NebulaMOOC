# 单点登录模块

用于管理用户信息的模块，其他模块通过GRPC调用这个模块的服务，基于protobuf协议。  
默认开启端口为15888。  
该模块设计为分布式，可通过多台服务器部署后配置Nginx的GRPC模块启用。  

# 包结构

- core                           -> 核心模块
- mybatis-spring-boot-starter    -> MyBatis 持久化框架
- mysql-connector-java           -> MySql连接驱动
- commons-pool2                  -> 提供lettuce pool 缓存连接池
- spring-boot-starter-data-redis -> 用于缓存用户登陆后的信息

# 目录结构

```
src
└── main
    ├── java
    │   └── com.nebula.mooc.ssoserver
    │                       ├── SsoServer.java  启动类
    │                       ├── config
    │                           ├── GRpcConfig.java     GRPC配置，开启用户服务
    │                       │   ├── RedisConfig.java    Redis配置
    │                       │   └── ServiceLog.java     服务日志记录
    │                       ├── dao
    │                       │   └── UserDao.java        用户数据接口层
    │                       └── service
    │                           ├── RedisService.java   Redis服务
    │                           └── UserService.java    用户服务
    └── resources
        ├── application.yml     配置redis使用的数据库
        └── mapper
            └── UserMapper.xml  用户数据读取SQL编写
```
