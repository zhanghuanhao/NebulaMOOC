/*
 * @author Zhanghh
 * @date 2019/4/12
 */
package com.nebula.mooc.webserver.util;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class FileUtil {

    private static final long jpg = 0xffd8ffL;
    private static final long png = 0x89504e47L;
    private static final long gif = 0x47494638L;

    /**
     * 临时文件目录
     */
    private static String tmpPath = System.getProperty("java.io.tmpdir") + File.separator;

    /**
     * 根据文件流读取图片的真实类型
     */
    public static boolean isImg(MultipartFile file) {
        byte[] b = new byte[4];
        try {
            InputStream inputStream = file.getInputStream();
            int length = inputStream.read(b, 0, b.length);
            if (length >= 4) {
                long type = bytesToHex(b);
                if ((type >> 8) == jpg || type == png || type == gif)
                    return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * byte[]转十六进制，存到long中
     */
    private static long bytesToHex(byte[] src) {
        if (src.length < 1) return 0;
        long head = 0;
        for (byte x : src) {
            head <<= 8;
            head |= x & 0xFF;
        }
        return head;
    }

    /**
     * 将上传的文件转储在本地临时文件
     *
     * @param file           上传的文件
     * @param originFileName 原始文件名
     */
    public static File transferTo(MultipartFile file, String originFileName) throws IOException {
        // 放入本地tmp文件夹中
        File newFile = new File(tmpPath + originFileName);
        file.transferTo(newFile);
        return newFile;
    }

}
