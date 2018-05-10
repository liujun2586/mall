package cn.wolfcode.shop.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * 商品的sku属性
 */
@Setter
@Getter
public class ProductSkuProperty extends BaseDomain {

    private Long productSkuId;

    private String value;

    private String image;

    private SkuProperty skuProperty;
}