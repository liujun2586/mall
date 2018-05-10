package cn.wolfcode.shop.domain;

import lombok.Getter;
import lombok.Setter;

/**
 *商品订单sku属性对象
 */
@Setter@Getter
public class OrderProductProperty extends BaseDomain{
    //商品id
    private Long productId;
    //属性名称
    private String name;
    //属性值
    private String value;

}