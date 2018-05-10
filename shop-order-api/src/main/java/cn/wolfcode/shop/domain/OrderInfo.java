package cn.wolfcode.shop.domain;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 基础订单对象
 */
@Setter
@Getter
public class OrderInfo extends BaseDomain {
    //订单编号
    private String orderSn;
    //下单用户
    private Long userId;
    //订单总额
    private BigDecimal orderAmount = BigDecimal.ZERO;
    //付款方式,0为余额付款，1为在线付款，2为货到付款',
    private Integer payType;
    //订单状态：0为未确认，1为已确认，2,为已完成，3为取消',
    private Integer orderStatus;
    //'订单物流状态：0为未发货，1为已发货，2为确认收货，3为已退货',
    private Integer flowStatus;
    //'订单支付状态：0为未付款，1为付款',
    private Integer payStatus;
    //下单时间
    private Date orderTime;
    //手机号码
    private String phone;
    //'联系人名称',
    private String consignee;
    //国家
    private String country;
    //省份
    private String province;
    //城市
    private String city;
    //区
    private String district;
    //详细地址
    private String address;
    //邮编
    private String zipcode;

}