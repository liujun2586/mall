package cn.wolfcode.shop.controller;

import cn.wolfcode.shop.service.IShoppingCarService;
import cn.wolfcode.shop.vo.WSResponseVo;
import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;

/**
 * 购物接口
 */
@RestController
@RequestMapping("/shoppingCars")
@Validated //检验参数是否正常
public class ShoppingCarController {

    @Reference
    private IShoppingCarService shoppingCarService;

    /**
     * 添加购物车
     * @param productNums 商品数量
     * @param skusn sku编号
     * @param token 认证令牌
     * @return
     */
    @PostMapping
    public WSResponseVo addCar(Integer productNums,String skusn,@RequestHeader(value="token",required = false)@NotNull(message="认证令牌不能为空") String token){
        shoppingCarService.addCar(productNums,skusn,token);
        return new WSResponseVo();
    }
}
