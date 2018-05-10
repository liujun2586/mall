package cn.wolfcode.shop.vo;

import cn.wolfcode.shop.domain.Car;
import cn.wolfcode.shop.domain.Invoice;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 生成订单对象所接收的数据
 */
@Setter@Getter
public class GenOrderVo  implements Serializable{

    private String token;

    private List<Car> carList = new ArrayList<>();

    private Invoice invoice ;

    private Long  userAddressId;

    private PayVo pay;
}
