package cn.wolfcode.shop.service.impl;

import cn.wolfcode.shop.domain.*;
import cn.wolfcode.shop.mapper.ProductDetailsMapper;
import cn.wolfcode.shop.mapper.ProductImageMapper;
import cn.wolfcode.shop.mapper.ProductMapper;
import cn.wolfcode.shop.mapper.ProductPropertyValueMapper;
import cn.wolfcode.shop.qo.PageResult;
import cn.wolfcode.shop.qo.QueryObject;
import cn.wolfcode.shop.service.ICatalogService;
import cn.wolfcode.shop.service.IProductService;
import cn.wolfcode.shop.vo.ProductVO;
import com.alibaba.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * Created by leo on 2018/1/10.
 */
@Service
@Transactional
public class ProductServiceImpl implements IProductService {

    @Autowired
    private ProductMapper              productMapper;
    @Autowired
    private ProductDetailsMapper       productDetailsMapper;
    @Autowired
    private ProductImageMapper         productImageMapper;
    @Autowired
    private ProductPropertyValueMapper productPropertyValueMapper;
    @Autowired
    private ICatalogService            catalogService;


    @Override
    public List<Product> getAllProduct() {
        return productMapper.selectAll();
    }

    @Override
    public PageResult productPage(QueryObject qo) {
        int  totalCount = productMapper.queryCount(qo);
        List data       = productMapper.queryList(qo);
        return new PageResult(data, totalCount, qo.getCurrentPage(), qo.getPageSize());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(ProductVO productVO) {
        //保存商品对象
        productVO.getProduct().setCreateTime(new Date());
        productVO.getProduct().setLastModifiedDate(new Date());
        int record = productMapper.insert(productVO.getProduct());
        if (record > 0) {
            //保存商品详细
            productVO.getProductDetails().setProductId(productVO.getProduct().getId());
            productDetailsMapper.insert(productVO.getProductDetails());
            //保存商品相册
            List<ProductImage> productImagesList = productVO.getProductImagesList();
            productImagesList.forEach(image -> {
                if (image.getImagePath() != null && image.getImagePath().trim().length() > 0) {
                    image.setProductId(productVO.getProduct().getId());
                    productImageMapper.insert(image);
                }
            });
            //保存商品属性
            List<ProductPropertyValue> valueList = productVO.getProductPropertyValueList();
            valueList.forEach(property -> {
                // 为每个属性设置商品id
                property.setProductId(productVO.getProduct().getId());
                productPropertyValueMapper.insert(property);
            });
        }
    }

    @Override
    public Product getProductById(Long productId) {
        return productMapper.selectByPrimaryKey(productId);
    }

}
