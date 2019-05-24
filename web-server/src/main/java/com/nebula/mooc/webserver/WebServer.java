/*
 * @author Zhanghh
 * @date 2019/4/11
 */
package com.nebula.mooc.webserver;

import com.nebula.mooc.core.Constant;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@MapperScan("com.nebula.mooc.webserver.dao")
@SpringBootApplication
@EnableCaching
public class WebServer {

    /**
     * 初始化类型名字
     */
    private static void initKindMap() {
        Constant.KIND_MAP.put(0, "TOTAL");
        Constant.KIND_MAP.put(1, "Java");
        Constant.KIND_MAP.put(2, "C");
        Constant.KIND_MAP.put(3, "C++");
        Constant.KIND_MAP.put(4, "PHP");
        Constant.KIND_MAP.put(5, "C#");
        Constant.KIND_MAP.put(6, "Python");
        Constant.KIND_MAP.put(7, "SQL");
        Constant.KIND_MAP.put(8, "VB");
        Constant.KIND_MAP.put(9, "GO");
        Constant.KIND_MAP.put(10, "Shell");
    }

    public static void main(String[] args) {
        System.setProperty("module.name", "web-server");
        initKindMap();
        SpringApplication.run(WebServer.class, args);
    }

}