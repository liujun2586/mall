package cn.wolfcode.shop.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * 订单日志操作明细对象
 */
@Setter
@Getter
public class OrderAction extends BaseDomain {
    //订单id
    private Long orderId;

    //操作人
    private String actionUser;

    //订单状态
    private Integer orderStatus;

    //物流状态
    private Integer flowStatus;

    //支付状态
    private Integer payStatus;

    //操作发出的位置：0前台，1后台',
    private Integer actionPlace;

    //备注
    private String actionNote;

    //操作时间
    private Date actionTime;
}