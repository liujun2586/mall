package cn.wolfcode.shop.mapper;

import cn.wolfcode.shop.domain.SkuProperty;

import java.util.List;

/**
 * 属性相关mapper接口
 */
public interface SkuPropertyMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SkuProperty record);

    SkuProperty selectByPrimaryKey(Long id);

    List<SkuProperty> selectAll();

    int updateByPrimaryKey(SkuProperty record);

    /**
     * 根据分类id查询属性
     * @param catalogId
     * @return
     */
    List<SkuProperty> getByCatalogId(Long catalogId);

    /**
     * 根据商品查询sku属性
     * @param productId
     * @return
     */
    List<SkuProperty> queryByProductId(Long productId);
}