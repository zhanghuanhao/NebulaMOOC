# Nginx RTMP使用说明

这是一个用于直播的拉流及推流服务器，基于nginx-rtmp-module。

## 版本

Nginx: 1.16.0
nginx-rtmp-module: 1.2.1
OpenSSL: 1.1.1c
zlib: 1.2.11
pcre: 8.43

## 使用说明

### Windows

解压后直接运行Nginx.exe即可，使用stop.bat可停止此进程

### Linux

解压后将已编译好的nginx文件夹放入/usr/local中，并执行以下的命令即可运行

```sh
sudo mv nginx/ /usr/local
sudo /usr/local/nginx/sbin/nginx -c /usr/local/nginx/conf/nginx.conf
```

## Linux环境下的编译

**编译环境：Ubuntu 18.04**

### 检查系统是否安装zlib 、PCRE、OpenSSL等库

#### 1. zlib

官网下载地址：http://www.zlib.net/

```sh
wget http://www.zlib.net/zlib-1.2.11.tar.gz
tar xf zlib-1.2.11.tar.gz
cd zlib-1.2.11
./configure
make && make install
```

#### 2. PCRE

官网下载地址：https://ftp.pcre.org/pub/pcre
注意：pcre库建议不要使用pcre2系列的版本，会导致Nginx编译报错的问题。

```sh
wget https://ftp.pcre.org/pub/pcre/pcre-8.43.tar.gz
tar xf pcre-8.43.tar.gz
cd pcre-8.43
./configure
make && make install
```

#### 3.  OpenSSL

官网下载地址：https://www.openssl.org/source/

```sh
wget https://www.openssl.org/source/openssl-1.1.1c.tar.gz
tar xf openssl-1.1.1c.tar.gz
cd openssl-1.1.1c
./config
make && make install
```

#### 最后编译Nginx with rtmp

tips: --add-prefix=... 是你运行时自带的前缀，默认不设置则为/usr/local，可自行设置

```sh
git clone https://github.com/arut/nginx-rtmp-module.git
wget http://nginx.org/download/nginx-1.16.0.tar.gz
tar xf nginx-1.16.0.tar.gz
cd nginx-1.16.0
./configure --add-module=../nginx-rtmp-module --with-http_ssl_module
make && make install
```

可以看到在/usr/local下有一个nginx目录，其中包括了可执行文件
执行下面命令运行

```sh
sudo /usr/local/nginx/sbin/nginx -c /usr/local/nginx/conf/nginx.conf
```
