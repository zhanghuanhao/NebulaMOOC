/*
 * @author Zhanghh
 * @date 2019/4/13
 */
package com.nebula.mooc.core.util;

public class TokenUtil {

    public static void main(String[] args) {
        String token = "8F41F215C82EE62C50CB1F44D3127F67";
        long start = System.currentTimeMillis();
//        try {
//            MessageDigest md = MessageDigest.getInstance("md5");
//            byte md5[] =  md.digest(token.getBytes());
//            //BASE64Encoder encoder = new BASE64Encoder();encoder.encode(md5)
//            System.out.println(new String(md5));
//        } catch (NoSuchAlgorithmException e) {
//            e.printStackTrace();
//        }
        System.out.println((System.currentTimeMillis() - start));
    }
}
