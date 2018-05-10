package cn.wolfcode.shop.domain;

import lombok.Getter;
import lombok.Setter;

@Setter@Getter
public class Car extends BaseDomain {
    //用户id
    private Long userId;
    //skuid
    private Long skuId;
    //商品名称
    private String productName;
    //商品数量
    private Integer productNumber;
    //商品图片
    private String productImg;

}