package cn.wolfcode.shop.service.impl;

import cn.wolfcode.shop.domain.Property;
import cn.wolfcode.shop.mapper.PropertyMapper;
import cn.wolfcode.shop.service.IPropertyService;
import cn.wolfcode.shop.service.IPropertyValueService;
import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.config.annotation.Service;
import jdk.nashorn.internal.runtime.PropertyMap;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service
public class PropertyServiceImpl implements IPropertyService {
    @Autowired
    private PropertyMapper propertyMapper;

    @Autowired
    private IPropertyValueService propertyValueService;

    @Override
    public List<Property> getByCataLogId(Long catalogId) {
        return propertyMapper.getByCatalogId(catalogId);
    }

    @Override
    public void saveOrUpdate(Property property) {
        if(property.getId()!=null){
            propertyMapper.updateByPrimaryKey(property);
        }else{
            propertyMapper.insert(property);
        }
    }

    @Override
    public void delete(Long id) {
        propertyValueService.deleteByPropertyId(id);
        propertyMapper.deleteByPrimaryKey(id);
    }

}
