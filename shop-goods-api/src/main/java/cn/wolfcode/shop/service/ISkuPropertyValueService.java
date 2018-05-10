package cn.wolfcode.shop.service;

import cn.wolfcode.shop.domain.SkuPropertyValue;

import java.util.List;

/**
 * 属性值相关接口
 */
public interface ISkuPropertyValueService {
    /**
     * 根据属性查询所有值
     * @param SkuPropertyId
     * @return
     */
    List<SkuPropertyValue> getBySkuPropertyId(Long SkuPropertyId);

    /**
     * 批量跟新属性值
     * @param values
     */
    void batchUpdate(List<SkuPropertyValue> values);

    void delete(Long id);

    /**
     * 根据属性id删除值
     * @param id
     */
    void deleteBySkuPropertyId(Long id);
}
