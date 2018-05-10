package cn.wolfcode.shop.domain;

import lombok.Getter;
import lombok.Setter;

@Setter@Getter
public class PropertyValue extends BaseDomain {
    //属性id
    private Long propertyId;
    //值
    private String value;

}