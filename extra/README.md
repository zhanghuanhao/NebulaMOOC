# 开发文档

这个文件夹存储着该项目可能用到的应用的使用说明  
包括：

- FastDFS -> 分布式文件系统
- Nginx RTMP -> 基于Nginx-RTMP-module的推流服务器
- Protobuf -> Google Protobuf协议
- Certificate -> SSL证书
- run.sh -> 服务器上一键后台运行脚本
- nginx-rtmp-fastdfs.zip -> 包括了RTMP模块和FastDFS模块的Nginx服务器

## 开发环境

Windows 10、IDEA、Java 8、Maven

## 部署环境

部署在阿里云，服务器配置为1G内存、单核、1Mbps、40G SSD。操作系统使用Ubuntu Server 18，使用MySQL作为数据库，Redis作为缓存。

开放端口包括：  

Web服务：80、443  

直播服务器：8080（用于Nginx回调）、8443、9080（WebSocket聊天服务）  

数据库服务器：3306  

Redis缓存服务器：6379  

FastDFS文件服务：10080、10443、22122、23000、23001  

Nginx RTMP流服务器：1935  

单点登陆服务器：15888  

## 安装说明（单机，分布式类似）

### 1. Redis

版本：4.0.9

#### (1) 安装

```bash
apt update
apt install redis-server
```

#### (2) 配置

```bash
vi /etc/redis/redis.conf
```

#### (3) 修改以下内容

```conf
protected-mode yes -> protected no

bind 127.0.0.1 ::1 -> # bind 127.0.0.1 ::1

# requirepass foobared -> requirepass Mooc123456

databases 16 -> databases 4
```

#### (4) 重启Redis并查看其状态

```bash
systemctl restart redis
systemctl status redis
```

### 2. MySQL

版本：5.7.26

#### (1) 安装

```bash
apt install mysql-server
```

#### (2) 配置，按提示完成

```bash
mysql_secure_installation
```

```bash
Securing the MySQL server deployment.                                   

Connecting to MySQL using a blank password.

VALIDATE PASSWORD PLUGIN can be used to test passwords
and improve security. It checks the strength of password
and allows the users to set only those passwords which are
secure enough. Would you like to setup VALIDATE PASSWORD plugin?

Press y|Y for Yes, any other key for No: y

There are three levels of password validation policy:

LOW    Length >= 8
MEDIUM Length >= 8, numeric, mixed case, and special characters
STRONG Length >= 8, numeric, mixed case, special characters and dictionary                  file

Please enter 0 = LOW, 1 = MEDIUM and 2 = STRONG: 2
Please set the password for root here.

New password: (Mooc123456)

Re-enter new password: (Mooc123456)

Estimated strength of the password: 50 
Do you wish to continue with the password provided?(Press y|Y for Yes, any other key for No) : y
By default, a MySQL installation has an anonymous user,
allowing anyone to log into MySQL without having to have
a user account created for them. This is intended only for
testing, and to make the installation go a bit smoother.
You should remove them before moving into a production
environment.

Remove anonymous users? (Press y|Y for Yes, any other key for No) : y
Success.


Normally, root should only be allowed to connect from
'localhost'. This ensures that someone cannot guess at
the root password from the network.

Disallow root login remotely? (Press y|Y for Yes, any other key for No) : n

 ... skipping.
By default, MySQL comes with a database named 'test' that
anyone can access. This is also intended only for testing,
and should be removed before moving into a production
environment.


Remove test database and access to it? (Press y|Y for Yes, any other key for No) : y
 - Dropping test database...
Success.

 - Removing privileges on test database...
Success.

Reloading the privilege tables will ensure that all changes
made so far will take effect immediately.

Reload privilege tables now? (Press y|Y for Yes, any other key for No) : y
Success.

All done! 
```

#### (3) 设置远程连接

```bash
vi /etc/mysql/mysql.conf.d/mysqld.cnf
```

```bash
bind-address = 127.0.0.1 -> bind-address = 0.0.0.0
```

另外需要进入 MySQL 程序修改 root 账户的远程访问的权限。如果这一步不执行，则远程用 Navicat 访问时，会报 1130 错误。

进入 MySQL 并输入

```bash
mysql -u root -p
> set global validate_password_policy=0; 
> GRANT ALL PRIVILEGES ON *.* TO 'root'@'%' IDENTIFIED BY 'Mooc123456' WITH GRANT OPTION;
> flush privileges;
```

#### (4) 重新启动服务器

```bash
systemctl restart mysql
systemctl status mysql
```

#### (5) 创建数据库

```
CREATE DATABASE IF NOT EXISTS mooc DEFAULT CHARSET utf8 COLLATE utf8_general_ci;
```

导入数据请执行mooc_data.sql，仅创建结构请执行mooc_struct.sql

### 3. FastDFS

可查看FastDFS目录下的README.md安装

### 4. Nginx RTMP服务器

同查看Nginx RTMP目录下的README.md安装

### 5. 编辑运行

```bash
mvn clean install
mvn install
```

可在目录下生成target目录，取出其中的jar包。将三个模块的jar包和run.sh放入同一目录，并执行run.sh
