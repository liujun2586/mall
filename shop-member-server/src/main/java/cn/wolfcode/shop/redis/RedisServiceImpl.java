package cn.wolfcode.shop.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class RedisServiceImpl {

    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

    /**
     * 设置值到redis中
     * @param key
     * @param data 需要缓存的数据
     * @param dateTime 超时时间
     */
    public void set(String key,Object data,int dateTime){
        getValueOperations().set(key,data,dateTime, TimeUnit.DAYS);
    }

    /**
     *
     * @param key
     * @param <T>
     * @return
     */
    public <T> T get(String key){
        return (T) getValueOperations().get(key);
    }

    private ValueOperations<String, Object> getValueOperations(){
        return redisTemplate.opsForValue();
    }

    /**
     * 删除缓存中的信息
     * @param key
     */
    public void remove(String key) {
        redisTemplate.delete(key);
    }
}
