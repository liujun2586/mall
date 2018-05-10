package cn.wolfcode.shop.mapper;

import cn.wolfcode.shop.domain.ProductSku;
import java.util.List;

public interface ProductSkuMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ProductSku record);

    ProductSku selectByPrimaryKey(Long id);

    List<ProductSku> selectAll();

    int updateByPrimaryKey(ProductSku record);

    /**
     * 根据商品查询sku
     * @param productId
     * @return
     */
    List<ProductSku> getByProductId(Long productId);

    /**
     * 根据编号查询sku对象
     * @param sn
     * @return
     */
    ProductSku getBySn(String sn);
}