package cn.wolfcode.shop.service.impl;

import cn.wolfcode.shop.domain.SkuProperty;
import cn.wolfcode.shop.mapper.SkuPropertyMapper;
import cn.wolfcode.shop.service.ISkuPropertyService;
import cn.wolfcode.shop.service.ISkuPropertyValueService;
import com.alibaba.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service
public class SkuPropertyServiceImpl implements ISkuPropertyService {
    @Autowired
    private SkuPropertyMapper skuPropertyMapper;

    @Autowired
    private ISkuPropertyValueService skuPropertyValueService;

    @Override
    public List<SkuProperty> getByCataLogId(Long catalogId) {
        return skuPropertyMapper.getByCatalogId(catalogId);
    }

    @Override
    public void saveOrUpdate(SkuProperty skuProperty) {
        if(skuProperty.getId()!=null){
            skuPropertyMapper.updateByPrimaryKey(skuProperty);
        }else{
            skuPropertyMapper.insert(skuProperty);
        }
    }

    @Override
    public void delete(Long id) {
        skuPropertyValueService.deleteBySkuPropertyId(id);
        skuPropertyMapper.deleteByPrimaryKey(id);
    }

    @Override
    public SkuProperty getById(Long id) {
        return skuPropertyMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<SkuProperty> queryByProductId(Long productId) {
        return skuPropertyMapper.queryByProductId(productId);
    }

}
