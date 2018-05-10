package cn.wolfcode.shop.mapper;

import cn.wolfcode.shop.domain.PropertyValue;
import java.util.List;

/**
 * 属性值相关接口
 */
public interface PropertyValueMapper {
    int deleteByPrimaryKey(Long id);

    int insert(PropertyValue record);

    PropertyValue selectByPrimaryKey(Long id);

    List<PropertyValue> selectAll();

    int updateByPrimaryKey(PropertyValue record);

    /**
     * 根据属性查询所有值列表
     * @param propertyId
     * @return
     */
    List<PropertyValue> getByPropertyId(Long propertyId);

    void deleteByPropertyId(Long propertyId);
}