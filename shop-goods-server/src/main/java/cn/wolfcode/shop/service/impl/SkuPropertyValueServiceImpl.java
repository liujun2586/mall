package cn.wolfcode.shop.service.impl;

import cn.wolfcode.shop.domain.SkuPropertyValue;
import cn.wolfcode.shop.mapper.SkuPropertyValueMapper;
import cn.wolfcode.shop.service.ISkuPropertyValueService;
import com.alibaba.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service
public class SkuPropertyValueServiceImpl implements ISkuPropertyValueService {

    @Autowired
    private SkuPropertyValueMapper valueMapper;

    @Override
    public List<SkuPropertyValue> getBySkuPropertyId(Long skuPropertyId) {
        return valueMapper.getBySkuPropertyId(skuPropertyId);
    }

    @Override
    public void batchUpdate(List<SkuPropertyValue> values) {
        for (SkuPropertyValue value : values) {
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
    public void deleteBySkuPropertyId(Long skuPropertyId) {
        valueMapper.deleteBySkuPropertyId(skuPropertyId);
    }
}
