package cn.wolfcode.shop.mapper;

import cn.wolfcode.shop.domain.Property;
import java.util.List;

/**
 * 属性相关mapper接口
 */
public interface PropertyMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Property record);

    Property selectByPrimaryKey(Long id);

    List<Property> selectAll();

    int updateByPrimaryKey(Property record);

    /**
     * 根据分类id查询属性
     * @param catalogId
     * @return
     */
    List<Property> getByCatalogId(Long catalogId);
}