# 解决Maven无法下载fastdfs-client-java依赖

```sh
git clone https://github.com/happyfish100/fastdfs-client-java.git
cd /fastdfs-client-java 
mvn clean install
```

pom.xml 可以引入了
```xml
<dependency>
    <groupId>org.csource</groupId>
    <artifactId>fastdfs-client-java</artifactId>
    <version>1.27-SNAPSHOT</version>
</dependency>
```