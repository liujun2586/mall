package cn.wolfcode.shop.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
@Setter@Getter
public class UserInfo extends BaseDomain {
    //昵称
    private String nickName;
    //真实姓名
    private String realName;
    //头像
    private String headImg;
    //身份证号码
    private String idNumber;
    //电话
    private String phone;
    //邮箱
    private String email;
    //性别
    private Boolean sex;
    //生日
    private Date birthday;
    //所在省
    private String province;
    //所在城市
    private String city;
    //所在地区
    private String district;
    //注册时间
    private Date registTime;

}