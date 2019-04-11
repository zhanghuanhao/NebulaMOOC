package com.nebula.mooc.core.store;

import com.nebula.mooc.core.entity.Constant;
import com.nebula.mooc.core.entity.User;
import com.nebula.mooc.core.util.JedisUtil;

/**
 * local login store
 *
 * @author xuxueli 2018-04-02 20:03:11
 */
public class SsoLoginStore {

    private static int redisExpireMinite = 1440;    // 1440 minite, 24 hour

    public static int getRedisExpireMinite() {
        return redisExpireMinite;
    }

    public static void setRedisExpireMinite(int redisExpireMinite) {
        if (redisExpireMinite < 30) {
            redisExpireMinite = 30;
        }
        SsoLoginStore.redisExpireMinite = redisExpireMinite;
    }

    /**
     * get
     *
     * @param storeKey
     * @return
     */
    public static User get(String storeKey) {

        String redisKey = redisKey(storeKey);
        Object objectValue = JedisUtil.getObjectValue(redisKey);
        if (objectValue != null) {
            User xxlUser = (User) objectValue;
            return xxlUser;
        }
        return null;
    }

    /**
     * remove
     *
     * @param storeKey
     */
    public static void remove(String storeKey) {
        String redisKey = redisKey(storeKey);
        JedisUtil.del(redisKey);
    }

    /**
     * put
     *
     * @param storeKey
     * @param xxlUser
     */
    public static void put(String storeKey, User xxlUser) {
        String redisKey = redisKey(storeKey);
        JedisUtil.setObjectValue(redisKey, xxlUser, redisExpireMinite * 60);  // minite to second
    }

    private static String redisKey(String sessionId) {
        return Constant.SESSION_ID.concat("#").concat(sessionId);
    }

}
