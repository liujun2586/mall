package cn.wolfcode.shop.domain;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * 订单商品明细对象
 */
@Setter@Getter
public class OrderProduct extends BaseDomain{
    //订单id
    private Long orderId;
    //skuid
    private Long skuId;
    //商品名称
    private String productName;
    //购买数量
    private Integer productNumber;
    //商品价格
    private BigDecimal productPrice;
    //小计
    private BigDecimal totalPrice;
    //商品图片
    private String productImg;
}