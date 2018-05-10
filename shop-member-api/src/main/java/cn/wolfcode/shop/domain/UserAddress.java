package cn.wolfcode.shop.domain;

import lombok.Getter;
import lombok.Setter;

@Setter@Getter
public class UserAddress extends BaseDomain{

    private Long userId;

    private String consignee;

    private String phone;

    private String country;

    private String province;

    private String city;

    private String district;

    private String address;

    private String zipcode;

    private Byte status;

}