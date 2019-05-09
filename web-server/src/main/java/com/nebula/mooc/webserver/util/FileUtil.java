/*
 * @author Zhanghh
 * @date 2019/5/9
 */
package com.nebula.mooc.webserver.util;

import com.aliyun.oss.OSSClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FileUtil {

    @Autowired
    private OSSClient ossClient;

}
