package cn.wolfcode.shop.redisUtil;

/**
 * key前缀的接口
 */
public interface KeyPrefix {
    /**
     * 获取key的前缀
     * @return
     */
    String getPrefix();

    String getRealKey(String token);
}
