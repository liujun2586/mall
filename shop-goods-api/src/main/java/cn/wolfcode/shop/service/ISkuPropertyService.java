package cn.wolfcode.shop.service;

import cn.wolfcode.shop.domain.Property;
import cn.wolfcode.shop.domain.SkuProperty;

import java.util.List;

/**
 * 操作属性相关服务接口
 */
public interface ISkuPropertyService {
    /**
     * 根据目录查询所有属性
     * @param catalogId
     * @return
     */
    List<SkuProperty> getByCataLogId(Long catalogId);

    void saveOrUpdate(SkuProperty property);

    void delete(Long id);

    SkuProperty getById(Long id);

    /**
     * 根据商品id查询属性
     * @param productId
     * @return
     */
    List<SkuProperty> queryByProductId(Long productId);
}
