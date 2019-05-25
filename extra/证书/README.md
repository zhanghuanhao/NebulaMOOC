# 证书生成说明

证书是通过[阿里云](https://common-buy.aliyun.com/?spm=5176.2020520163.cas.2.7dc6N26bN26bWD&commodityCode=cas#/buy)（免费型DV SSL）申请的免费证书，2020-05-06过期，挂载域名为www.nebulamooc.top

步骤：

1. 申请成功后，下载证书中的tomcat类型（因spring使用tomcat容器），替换web-server -> resources -> nebulamooc.pfx

2. 修改web-server -> resources -> application.yml中的证书信息（密码和证书名）

3. 下载其他类型，包括了pem和key，key需执行下面的命令进行转换：

   ```sh
   openssl pkcs8 -topk8 -nocrypt -in nebulamooc.key -out nebulamooc.new.key
   ```

4. 替换live-server -> resources -> .key和.pem文件，并修改application.yml中的文件名

5. 网页服务器和聊天服务器的SSL就搭建完成啦~~

参考网址：https://www.waspring.com/view/129

