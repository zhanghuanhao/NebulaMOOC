# FastDFS部署说明

这是一个用于存储文件（图片、视频等）的分布式文件系统，下面为单机部署教程。  
**在配置时记得关闭防火墙，如在阿里云的防火墙中开放端口10080、10443、22122、23000、23001**  
参考网址：https://blog.51cto.com/itstyle/2073797

## 版本

libfastcommon: 1.0.39
FastDFS: 5.11
fastdfs-nginx-module: 1.20
Nginx: 1.16.0
zlib: 1.2.11
pcre: 8.43

## 部署说明

### 1. 安装libfastcommon

```sh
wget https://github.com/happyfish100/libfastcommon/archive/V1.0.39.tar.gz
tar -xvf V1.0.39.tar.gz
cd libfastcommon-1.0.39
./make.sh
./make.sh install
ln -s /usr/lib64/libfastcommon.so /usr/local/lib/libfastcommon.so
ln -s /usr/lib64/libfastcommon.so /usr/lib/libfastcommon.so
ln -s /usr/lib64/libfdfsclient.so /usr/local/lib/libfdfsclient.so
ln -s /usr/lib64/libfdfsclient.so /usr/lib/libfdfsclient.so 
```

### 2. 安装FastDFS

```sh
wget https://github.com/happyfish100/fastdfs/archive/V5.11.tar.gz
tar -xvf V5.11.tar.gz
cd fastdfs-5.11
./make.sh
./make.sh install
```

### 3. 配置 Tracker 服务

上述安装成功后，在/etc/目录下会有一个fdfs的目录，进入后，会看到三个.sample后缀的文件，这是作者给我们的示例文件，我们需要把其中的tracker.conf.sample文件改为tracker.conf配置文件并修改：

```sh
cd /etc/fdfs
cp tracker.conf.sample tracker.conf
vi tracker.conf
```

编辑tracker.conf

```sh
# 配置文件是否不生效，false 为生效
disabled=false
# 提供服务的端口
port=22122
# Tracker 数据和日志目录地址
base_path=/etc/fastdfs/tracker
```

创建tracker基础数据目录，即base_path对应的目录，并添加服务

```sh
mkdir -p /etc/fastdfs/tracker
ln -s /usr/bin/fdfs_trackerd /usr/local/bin
ln -s /usr/bin/stop.sh /usr/local/bin
ln -s /usr/bin/restart.sh /usr/local/bin
systemctl daemon-reload
```

启动服务

```sh
service fdfs_trackerd start
```

查看监听

```sh
netstat -unltp|grep fdfs
```

如果看到22122端口正常被监听后，这时候说明Tracker服务启动成功啦！

### 4. 配置 Group: image 的 Storage 服务

进入 /etc/fdfs 目录，复制 FastDFS 存储器样例配置文件 storage.conf.sample，并重命名为 storage.conf

```sh
cd /etc/fdfs
cp storage.conf.sample storage.conf
vi storage.conf
```

编辑storage.conf

```sh
# 配置文件是否不生效，false 为生效
disabled=false
# 指定此 storage server 所在 组(卷)
group_name=image
# storage server 服务端口
port=23000
# 心跳间隔时间，单位为秒 (这里是指主动向 tracker server 发送心跳)
heart_beat_interval=30
# Storage 数据和日志目录地址(根目录必须存在，子目录会自动生成)
base_path=/etc/fastdfs/storage/image/base
# 存放文件时 storage server 支持多个路径。这里配置存放文件的基路径数目，通常只配一个目录。
store_path_count=1
# 逐一配置 store_path_count 个路径，索引号基于 0。
# 如果不配置 store_path0，那它就和 base_path 对应的路径一样。
store_path0=/etc/fastdfs/storage/image
# FastDFS 存储文件时，采用了两级目录。这里配置存放文件的目录个数。 
# 如果本参数只为 N（如： 256），那么 storage server 在初次运行时，会在 store_path 下自动创建 N * N 个存放文件的子目录。
subdir_count_per_path=256
# tracker_server 的列表 ，会主动连接 tracker_server
# 有多个 tracker server 时，每个 tracker server 写一行
# ip地址不要写 127.0.0.1，要写（ifconfig命令后显示的那个ip地址）
tracker_server=服务器IP:22122
# 允许系统同步的时间段 (默认是全天) 。一般用于避免高峰同步产生一些问题而设定。
sync_start_time=00:00
sync_end_time=23:59
```

创建目录及服务

```sh
mkdir -p /etc/fastdfs/storage/image/base
ln -s /usr/bin/fdfs_storaged /usr/local/bin
systemctl daemon-reload
```

启动

```sh
/usr/bin/fdfs_storaged /etc/fdfs/storage.conf
```

查看监听

```sh
netstat -unltp|grep fdfs
```

启动Storage前确保Tracker是启动的。如果看到23000端口正常被监听后，这时候说明image的Storage服务启动成功啦！

### 5. 配置 Group: video 的 Storage 服务

进入 /etc/fdfs 目录，复制刚刚配置好的 storage.confe，并重命名为 storage2.conf

```sh
cd /etc/fdfs
cp storage.conf storage2.conf
vi storage2.conf
```

编辑storage.conf

```sh
# 配置文件是否不生效，false 为生效
disabled=false
# 指定此 storage server 所在 组(卷)
group_name=video
# storage server 服务端口
port=23001
# 心跳间隔时间，单位为秒 (这里是指主动向 tracker server 发送心跳)
heart_beat_interval=30
# Storage 数据和日志目录地址(根目录必须存在，子目录会自动生成)
base_path=/etc/fastdfs/storage/video/base
# 存放文件时 storage server 支持多个路径。这里配置存放文件的基路径数目，通常只配一个目录。
store_path_count=1
# 逐一配置 store_path_count 个路径，索引号基于 0。
# 如果不配置 store_path0，那它就和 base_path 对应的路径一样。
store_path0=/etc/fastdfs/storage/video
# FastDFS 存储文件时，采用了两级目录。这里配置存放文件的目录个数。 
# 如果本参数只为 N（如： 256），那么 storage server 在初次运行时，会在 store_path 下自动创建 N * N 个存放文件的子目录。
subdir_count_per_path=256
# tracker_server 的列表 ，会主动连接 tracker_server
# 有多个 tracker server 时，每个 tracker server 写一行
# ip地址不要写 127.0.0.1，要写（ifconfig命令后显示的那个ip地址）
tracker_server=服务器IP:22122
# 允许系统同步的时间段 (默认是全天) 。一般用于避免高峰同步产生一些问题而设定。
sync_start_time=00:00
sync_end_time=23:59
```

创建目录

```sh
mkdir -p /etc/fastdfs/storage/video/base
```

启动

```sh
/usr/bin/fdfs_storaged /etc/fdfs/storage2.conf
```

查看监听

```sh
netstat -unltp|grep fdfs
```

如果看到23001端口正常被监听后，这时候说明video的Storage服务启动成功啦！

可执行下列语句查看tracker情况

```sh
/usr/bin/fdfs_monitor /etc/fdfs/storage.conf
```

### 6. 配置 Nginx 服务

用于客户端访问其中的图片和视频
目录下有已编译好的nginx文件，因此将文件解压后复制即可

```sh
cp -rf nginx/ /usr/local
cp -rf fdfs/ /etc
```

或者自己编译nginx：

#### 1. zlib

```sh
wget http://www.zlib.net/zlib-1.2.11.tar.gz
tar xf zlib-1.2.11.tar.gz
cd zlib-1.2.11
./configure
make && make install
```

#### 2. PCRE

```sh
wget https://ftp.pcre.org/pub/pcre/pcre-8.43.tar.gz
tar xf pcre-8.43.tar.gz
cd pcre-8.43
./configure
make && make install
```

#### 3.  OpenSSL

```sh
wget https://www.openssl.org/source/openssl-1.1.1c.tar.gz
tar xf openssl-1.1.1c.tar.gz
cd openssl-1.1.1c
./config
make && make install
```

#### 4. fastdfs-nginx-module

```sh
wget https://github.com/happyfish100/fastdfs-nginx-module/archive/V1.20.tar.gz
tar xf V1.20.tar.gz
vim fastdfs-nginx-module-1.20/src/config
```

编辑 fastdfs-nginx-module-1.20/src/config 文件

```sh
ngx_addon_name=ngx_http_fastdfs_module

if test -n "${ngx_module_link}"; then
ngx_module_type=HTTP
ngx_module_name=$ngx_addon_name
ngx_module_incs="/usr/include/fastdfs /usr/include/fastcommon/"
ngx_module_libs="-lfastcommon -lfdfsclient"
ngx_module_srcs="$ngx_addon_dir/ngx_http_fastdfs_module.c"
ngx_module_deps=
CFLAGS="$CFLAGS -D_FILE_OFFSET_BITS=64 -DFDFS_OUTPUT_CHUNK_SIZE='2561024' -DFDFS_MOD_CONF_FILENAME='"/etc/fdfs/mod_fastdfs.conf"'"
. auto/module
else
HTTP_MODULES="$HTTP_MODULES ngx_http_fastdfs_module"
NGX_ADDON_SRCS="$NGX_ADDON_SRCS $ngx_addon_dir/ngx_http_fastdfs_module.c"
CORE_INCS="$CORE_INCS /usr/include/fastdfs /usr/include/fastcommon/"
CORE_LIBS="$CORE_LIBS -lfastcommon -lfdfsclient"
CFLAGS="$CFLAGS -D_FILE_OFFSET_BITS=64 -DFDFS_OUTPUT_CHUNK_SIZE='2561024' -DFDFS_MOD_CONF_FILENAME='"/etc/fdfs/mod_fastdfs.conf"'"
fi
```

改变的文件内容

**ngx_module_incs="/usr/include/fastdfs /usr/include/fastcommon/"
CORE_INCS="$CORE_INCS /usr/include/fastdfs /usr/include/fastcommon/"**

#### 5. 编译Nginx

```sh
wget http://nginx.org/download/nginx-1.16.0.tar.gz
tar xf nginx-1.16.0.tar.gz
cd nginx-1.16.0
./configure --add-module=../fastdfs-nginx-module-1.20/src --with-http_ssl_module
make && make install
```

#### 6. 编辑配置文件

复制 fastdfs-nginx-module 源码中的配置文件及**fasts-dfs-5.11中的http.conf和mime.types**到/etc/fdfs 目录，否则无法启动nginx：

```sh
cp fastdfs-nginx-module-1.20/src/mod_fastdfs.conf /etc/fdfs/
cp fastdfs-5.11/conf/http.conf /etc/fdfs/
cp fastdfs-5.11/conf/mime.types /etc/fdfs/
vim /etc/fdfs/mod_fastdfs.conf
```

```sh
# 连接超时时间
connect_timeout=10
# Tracker Server
# ip地址不要写 127.0.0.1，要写（ifconfig命令后显示的那个ip地址）
tracker_server=服务器IP:22122
# StorageServer 默认端口
storage_server_port=23000
# 如果文件ID的uri中包含/group**，则要设置为true
url_have_group_name = true
# 组数
group_count = 2
# 图片存储组
[group1]
group_name=image
storage_server_port=23000
store_path_count=1
store_path0=/etc/fastdfs/storage/image
# 视频存储组
[group2]
group_name=video
storage_server_port=23001
store_path_count=1
store_path0=/etc/fastdfs/storage/video
```

复制证书（.pem、.key）到/usr/local/nginx/conf，并修改nginx.conf：

```sh
cp 项目根路径/extra/证书/nebulamooc.pem /usr/local/nginx/conf
cp 项目根路径/extra/证书/nebulamooc.key /usr/local/nginx/conf
vim /usr/local/nginx/conf/nginx.conf
```

```conf
user root;

http{
    server{
        listen 10080;
        location ~ [image,video]/M00 {
            ngx_fastdfs_module;
        }
    }

    # HTTPS server
    server {
        listen       10443 ssl;
        server_name  localhost;

        ssl_certificate      nebulamooc.pem;
        ssl_certificate_key  nebulamooc.key;

        location ~ [image,video]/M00 {
            ngx_fastdfs_module;
        }
    }
}
```

运行Nginx

```sh
/usr/local/nginx/sbin/nginx
```

测试，访问http://服务器IP:10080和https://服务器IP:10443是否有nginx欢迎页，有则成功啦~
访问图片url：http://服务器IP/M00/00/00/xxxx.jpg
