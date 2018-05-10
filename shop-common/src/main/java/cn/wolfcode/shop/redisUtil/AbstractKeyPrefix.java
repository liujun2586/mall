package cn.wolfcode.shop.redisUtil;

abstract public class AbstractKeyPrefix implements KeyPrefix {

    public static final String USERKEY_PREFIX = "token";

    /**
     * 获取key的前缀
     * @return
     */
    public String getPrefix(){
        return null;
    }

    public String getRealKey(String token){
        return null;
    }

}
