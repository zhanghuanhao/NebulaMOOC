/*
 * @author Zhanghh
 * @date 2019/5/8
 */
package com.nebula.mooc.webserver.util;

import net.coobird.thumbnailator.Thumbnails;

import java.io.IOException;
import java.io.InputStream;

public class ImgUtil {

    private static final int defaultSize = 40;


    public static void resize(InputStream inputStream, String path) throws IOException {
        /**
         * keepAspectRatio(false) 默认是按照比例缩放的
         * scale(0.25f)
         * outputQuality(0.8f)
         */
        Thumbnails.of(inputStream).size(defaultSize, defaultSize)
                .keepAspectRatio(false).outputFormat("png").toFile(path);
    }
}
