package cn.wolfcode.shop.domain;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

@Setter@Getter
public class UserLogin  extends BaseDomain{
    /**
     * 正常
     */
    public  static final Integer STATU_IN_NOMAL = 0;
    /**
     * 注销
     */
    public static final Integer STATU_IN_DELETE = 1;
    /**
     *异常
     */
    public static final Integer STATU_IN_ERROR = 1;
    @NotNull(message = "用户名不能为空")
    private String username;
    @Length(min = 4,max = 12,message = "密码长度只能在4到12位之间")
    private String password;

    private Integer status;

}