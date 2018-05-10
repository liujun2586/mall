package cn.wolfcode.shop.mapper;

import cn.wolfcode.shop.domain.SkuPropertyValue;

import java.util.List;

/**
 * 属性值相关接口
 */
public interface SkuPropertyValueMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SkuPropertyValue record);

    SkuPropertyValue selectByPrimaryKey(Long id);

    List<SkuPropertyValue> selectAll();

    int updateByPrimaryKey(SkuPropertyValue record);

    /**
     * 根据属性查询所有值列表
     * @param skuPropertyId
     * @return
     */
    List<SkuPropertyValue> getBySkuPropertyId(Long skuPropertyId);

    void deleteBySkuPropertyId(Long skuPropertyId);
}