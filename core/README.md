# 核心模块

所有模块依赖于此，包括了一些通用的类和配置

# 包结构

- spring-boot-starter-aop -> Spring AOP 主要用于日志记录
- grpc-netty、grpc-protobuf、grpc-stub -> Google RPC远程服务调用

# 目录结构
```
src
└── main
    ├── java
    │   └── com.nebula.mooc.core
    │                       ├── Constant.java   一些常量
    │                       ├── UserMessage.java   基于Protobuf生成的用户信息
    │                       ├── entity  一些实体对象
    │                       ├── service
    │                       │   └── UserServiceGrpc.java    基于Protobuf生成的用户服务
    │                       └── util
    │                           ├── TokenUtil.java  用于生成Token
    │                           └── TypeUtil.java   Protobuf生成的对象与实体对象转换
    └── resources
        ├── application-core.yml    配置地址信息，数据库、Redis、MyBatis配置等
        ├── banner.txt  项目横幅
        └── logback.xml 日志管理配置
```
