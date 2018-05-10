package cn.wolfcode.shop.domain;

import lombok.Getter;
import lombok.Setter;

@Setter@Getter
public class SkuPropertyValue extends BaseDomain {
    //属性id
    private Long skuPropertyId;
    //值
    private String value;

}