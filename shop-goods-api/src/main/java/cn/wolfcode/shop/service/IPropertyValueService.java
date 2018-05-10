package cn.wolfcode.shop.service;

import cn.wolfcode.shop.domain.PropertyValue;

import java.util.List;

/**
 * 属性值相关接口
 */
public interface IPropertyValueService {
    /**
     * 根据属性查询所有值
     * @param propertyId
     * @return
     */
    List<PropertyValue> getByPropertyId(Long propertyId);

    /**
     * 批量跟新属性值
     * @param values
     */
    void batchUpdate(List<PropertyValue> values);

    void delete(Long id);

    /**
     * 根据属性id删除值
     * @param id
     */
    void deleteByPropertyId(Long id);
}
