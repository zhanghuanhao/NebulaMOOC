/*
 * @author Zhanghh
 * @date 2019/4/13
 */
package com.nebula.mooc.core.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

/**
 * 生成token工具类
 */
public class TokenUtil {

    private static MessageDigest messageDigest;

    static {
        try {
            messageDigest = MessageDigest.getInstance("SHA-1");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    /**
     * byte转换为String，防止因乱码无法设置Cookie
     *
     * @param digest 经散列后的摘要
     * @return 返回String
     */
    private static String byteToString(byte[] digest) {
        StringBuilder sb = new StringBuilder(digest.length << 1);
        for (byte a : digest) {
            String temp = Integer.toHexString(a & 255); // &255使负数变为正
            sb.append(temp);
        }
        return sb.toString();
    }

    /**
     * 获取经过md5散列后的token值
     *
     * @param sessionId 传入sessionId
     * @return 返回生成的token
     */
    public static String generateToken(String sessionId){
        if (sessionId == null) return null;
        //添加当前时间和UUID增加复杂性
        sessionId += System.currentTimeMillis() + UUID.randomUUID().toString();
        byte[] md5 = messageDigest.digest(sessionId.getBytes());
        return byteToString(md5);
    }

}
