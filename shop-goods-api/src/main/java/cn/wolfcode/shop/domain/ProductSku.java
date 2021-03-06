package cn.wolfcode.shop.domain;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * 商品的sku模型
 */
@Setter@Getter
public class ProductSku  extends BaseDomain{

    private Long productId;

    private String sn;

    private BigDecimal price;

    private List<ProductSkuProperty> productSkuPropertyList = new ArrayList<>();
}