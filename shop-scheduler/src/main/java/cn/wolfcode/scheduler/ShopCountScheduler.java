package cn.wolfcode.scheduler;

import cn.wolfcode.shop.service.ICatalogService;
import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 定时统计任务类
 */
@Component
public class ShopCountScheduler {

    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

    @Reference
    private ICatalogService catalogService;

    @Scheduled(cron = "20 03 15 * * ?")
    public void selCountProducts(){
        //创建redis hash 数据结构
        HashOperations<String,String,Integer> hash = redisTemplate.opsForHash();
        //查询数据库中所有的商品分类
        List<Long> ids = catalogService.selectAllCatalogId();
        ids.forEach(id -> {
            //创建map用来存储数据
            Map<String,Integer> map = new HashMap<>();
            //查询数据库中所有分类的商品数量
            Integer productNums = catalogService.selProductNum(id);
            //查询数据库中所有分类的属性数量
            Integer propertyNums = catalogService.selPropertyNum(id);

            map.put("productNums",productNums);
            map.put("propertyNums",propertyNums);
            //将数据缓存到redis中
            hash.putAll("catalog:"+id,map);
        });
    }
}
