# 证书生成说明

证书是通过阿里云申请的免费证书，2020-05-06过期，挂载域名为www.nebulamooc.top

步骤：

1. 下载证书中的tomcat类型，替换web-server -> resources -> nebulamooc.pfx

2. 修改web-server -> resources -> application.yml中的密码

3. 下载其他类型，包括了pem和key，key需执行下面的命令进行转换：

   ```sh
   openssl pkcs8 -topk8 -nocrypt -in nebulamooc.key -out nebulamooc.new.key
   ```

4. 替换chat-server -> resources -> .key和.pem文件，并修改application.yml文件路径