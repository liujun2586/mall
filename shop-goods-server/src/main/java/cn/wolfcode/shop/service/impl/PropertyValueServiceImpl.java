package cn.wolfcode.shop.service.impl;

import cn.wolfcode.shop.domain.PropertyValue;
import cn.wolfcode.shop.mapper.PropertyValueMapper;
import cn.wolfcode.shop.service.IPropertyValueService;
import com.alibaba.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service
public class PropertyValueServiceImpl implements IPropertyValueService {

    @Autowired
    private PropertyValueMapper valueMapper;

    @Override
    public List<PropertyValue> getByPropertyId(Long propertyId) {
        return valueMapper.getByPropertyId(propertyId);
    }

    @Override
    public void batchUpdate(List<PropertyValue> values) {
        for (PropertyValue value : values) {
            if(value.getId()!=null){
                valueMapper.updateByPrimaryKey(value);
            }else{
                valueMapper.insert(value);
            }
        }
    }

    @Override
    public void delete(Long id) {
        valueMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void deleteByPropertyId(Long propertyId) {
        valueMapper.deleteByPropertyId(propertyId);
    }
}
