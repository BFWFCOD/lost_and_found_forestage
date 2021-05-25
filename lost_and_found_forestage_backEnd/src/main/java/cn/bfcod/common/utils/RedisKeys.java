

package cn.bfcod.common.utils;

/**
 * Redis所有Keys
 *
 * @author bfcod
 */
public class RedisKeys {

    public static String getSysConfigKey(String key){
        return "forestage:sys:config:" + key;
    }

    public static String getTokenKey(String key){
        return "forestage:token:" + key;
    }
}
