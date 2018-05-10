package cn.wolfcode.shop.domain;

import lombok.Getter;
import lombok.Setter;

/**
 * 商品的属性值
 */
@Setter@Getter
public class ProductPropertyValue extends BaseDomain {

    private Long productId;

    private String name;

    private String value;

}