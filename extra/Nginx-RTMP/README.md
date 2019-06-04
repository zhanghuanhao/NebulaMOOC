# Nginx RTMP使用说明

## 版本
编译环境：Ubuntu 18.04
Nginx: 1.16.0
nginx-rtmp-module: 1.2.1
OpenSSL: 1.1.1c
zlib: 1.2.11
pcre: 8.43

## 检查系统是否安装PCRE、OpenSSL、zlib 等库
### 1. zlib
官网下载地址：http://www.zlib.net/
```sh
wget http://www.zlib.net/zlib-1.2.11.tar.gz
tar xf zlib-1.2.11.tar.gz
cd zlib-1.2.11
./configure
make
```

### 2.  openssl
官网下载地址：https://www.openssl.org/source/
```sh
wget https://www.openssl.org/source/openssl-1.1.1c.tar.gz
tar xf openssl-1.1.1c.tar.gz
cd openssl-1.1.1c
./config
make
```
### 3. pcre
官网下载地址：https://ftp.pcre.org/pub/pcre
注意：pcre库建议不要使用pcre2系列的版本，会导致Nginx编译报错的问题。
```sh
wget https://ftp.pcre.org/pub/pcre/pcre-8.43.tar.gz
tar xf pcre-8.43.tar.gz
cd pcre-8.43
./configure
make
```

## 最后编译Nginx with rtmp
```sh
cd ~
git clone https://github.com/arut/nginx-rtmp-module.git
wget http://nginx.org/download/nginx-1.16.0.tar.gz
tar xf nginx-1.13.4.tar.gz
cd nginx-1.16.0
./configure --add-module=../nginx-rtmp-module --with-http_ssl_module  --with-pcre=../pcre-8.43 --with-openssl=../openssl-1.1.1c --with-zlib=../zlib-1.2.11
sudo make
sudo make install
```
可以看到有一个nginx目录，其中包括了可执行文件，编译成功并运行
```sh
sudo /usr/local/nginx/sbin/nginx -c /usr/local/nginx/conf/nginx.conf
```

或者将已编译后的nginx文件夹放入/usr/local中，并执行上面的命令

