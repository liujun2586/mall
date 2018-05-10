package cn.wolfcode.shop.controller;

import cn.wolfcode.shop.producer.OrderProducer;
import cn.wolfcode.shop.service.IOrderService;
import cn.wolfcode.shop.vo.GenOrderVo;
import cn.wolfcode.shop.vo.WSResponseVo;
import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;

/**
 * 订单相关接口
 */
@RestController
@RequestMapping("/orders")
public class OrderController {
    //@Reference
    //private IOrderService orderService;
    @Autowired
    private OrderProducer orderProducer;

    @PostMapping
    public WSResponseVo genOrder(@RequestBody GenOrderVo vo,@RequestHeader("token") String token){
        vo.setToken(token);
        orderProducer.sendMessage(vo);
        return  new WSResponseVo();
    }

}
