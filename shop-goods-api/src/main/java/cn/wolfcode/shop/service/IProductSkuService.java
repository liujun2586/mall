package cn.wolfcode.shop.service;

import cn.wolfcode.shop.domain.ProductSku;
import cn.wolfcode.shop.vo.GenerateVO;
import cn.wolfcode.shop.vo.ProductSkuVo;

import java.util.List;
import java.util.Map;

/**
 * 商品sku业务接口
 */
public interface IProductSkuService {
    /**
     * 生成sku的方法
     * @param generateVo
     */
    List<Map<String,Object>> generateSku(GenerateVO generateVo);

    void save(ProductSkuVo vo);

    /**
     * 根据商品的id查询商品sku
     * @param productId
     * @return
     */
    List<ProductSku> getByProductId(Long productId);

    /**
     * 根据当前sku的sn查询该商品sku信息
     * @param skusn
     * @return
     */
    ProductSku getBySn(String skusn);

    ProductSku getById(Long id);
}
