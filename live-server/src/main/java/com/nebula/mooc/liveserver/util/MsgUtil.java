/*
 * @author Zhanghh
 * @date 2019/5/31
 */
package com.nebula.mooc.liveserver.util;

import org.jsoup.Jsoup;
import org.jsoup.safety.Whitelist;

/**
 * 过滤XSS信息
 */
public class MsgUtil {

    private static final Whitelist whitelist = Whitelist.none().preserveRelativeLinks(true);

    /**
     * 清除XSS信息
     */
    public static String cleanMsg(String content) {
        return Jsoup.clean(content, whitelist);
    }

}
