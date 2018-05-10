package cn.wolfcode.shop.service.impl;

import cn.wolfcode.shop.domain.ProductSkuProperty;
import cn.wolfcode.shop.mapper.ProductSkuPropertyMapper;
import cn.wolfcode.shop.service.IProductSkuPropertyService;
import com.alibaba.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class ProductSkuPropertyServiceImpl implements IProductSkuPropertyService {
    @Autowired
    private ProductSkuPropertyMapper mapper;

    @Override
    public void insert(ProductSkuProperty skuProperty) {
        mapper.insert(skuProperty);
    }

}
