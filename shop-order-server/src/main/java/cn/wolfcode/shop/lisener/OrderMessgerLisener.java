package cn.wolfcode.shop.lisener;

import cn.wolfcode.shop.service.IOrderService;
import cn.wolfcode.shop.util.ConstantUtil;
import cn.wolfcode.shop.vo.GenOrderVo;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
@Component
public class OrderMessgerLisener {
    /**
     *
     * destination :消息接收的目标
     * containerFactory 监听哪种模型
     */
    @Autowired
    private IOrderService orderService;


    @JmsListener(destination = ConstantUtil.GENERATE_ORDER_MESSAGE,containerFactory="jmsListenerContainerQueue")
    public void receiveGenerateOrder(String messger) throws JMSException {
        orderService.genOrder((GenOrderVo)JSON.parse(messger));
    }
}
