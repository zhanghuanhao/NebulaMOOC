/*
 * @author Zhanghh
 * @date 2019/4/13
 */
package com.nebula.mooc.ssoserver.util;

import com.nebula.mooc.core.entity.LoginUser;

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
     * @param loginUser 传入用户信息
     * @return 返回生成的token
     */
    public static String generateToken(LoginUser loginUser) {
        //添加当前时间和UUID增加复杂性
        String token = System.currentTimeMillis() + UUID.randomUUID().toString()
                + loginUser.getUsername() + loginUser.getPassword();
        byte[] md5 = messageDigest.digest(token.getBytes());
        return byteToString(md5);
    }

}
