package cn.wolfcode.shop.service;

import cn.wolfcode.shop.domain.Property;

import java.util.List;

/**
 * 操作属性相关服务接口
 */
public interface IPropertyService {
    /**
     * 根据目录查询所有属性
     * @param catalogId
     * @return
     */
    List<Property> getByCataLogId(Long catalogId);

    void saveOrUpdate(Property property);

    void delete(Long id);

}
