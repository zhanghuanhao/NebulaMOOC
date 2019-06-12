/*
 * @author Zhanghh
 * @date 2019/4/12
 */
package com.nebula.mooc.webserver.util;

import com.nebula.mooc.core.util.TokenUtil;
import net.coobird.thumbnailator.Thumbnails;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class FileUtil {

    private static final long jpg = 0xffd8ffL;
    private static final long png = 0x89504e47L;
    private static final long gif = 0x47494638L;

    private static final int headSize = 400;

    /**
     * 临时文件目录
     */
    private static String tmpPath = System.getProperty("java.io.tmpdir");

    /**
     * 根据文件流读取图片的真实类型
     *
     * @return 返回文件类型：jpg、png、gif
     */
    public static String isImg(MultipartFile file) {
        if (file.getContentType() != null && file.getContentType().startsWith("image")) {
            byte[] b = new byte[4];
            try {
                InputStream inputStream = file.getInputStream();
                int length = inputStream.read(b, 0, b.length);
                if (length >= 4) {
                    long type = bytesToHex(b);
                    if ((type >> 8) == jpg) return "jpg";
                    if (type == png) return "png";
                    if (type == gif) return "gif";
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
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
     * @param file 上传的文件
     */
    public static File transferTo(MultipartFile file) throws IOException {
        String originFileName = file.getOriginalFilename();
        if (originFileName == null) originFileName = TokenUtil.generateToken();
        File newFile = File.createTempFile(originFileName, null);
        file.transferTo(newFile);
        return newFile;
    }

    /**
     * 调整图片大小
     *
     * @param file 上传的文件
     */
    public static File resizeImage(MultipartFile file) throws IOException {
        String originFileName = file.getOriginalFilename();
        if (originFileName == null) originFileName = TokenUtil.generateToken();
        File newFile = File.createTempFile(originFileName, null);
            Thumbnails.of(file.getInputStream())
                    .size(headSize, headSize)
                    .keepAspectRatio(false).toFile(newFile);
        return newFile;
    }

}
