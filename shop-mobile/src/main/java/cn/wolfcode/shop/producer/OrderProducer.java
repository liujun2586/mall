package cn.wolfcode.shop.producer;

import cn.wolfcode.shop.vo.GenOrderVo;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import javax.jms.Destination;

/**
 * 订单消息生产者
 */
@Component
public class OrderProducer {
    @Autowired
    private JmsTemplate jmsTemplate;
    @Autowired
    private Destination generateOrderDestinationQueue;

    /**
     * 发布订单创建消息
     */
    public void sendMessage(GenOrderVo vo){
        jmsTemplate.convertAndSend(generateOrderDestinationQueue, JSON.toJSONString(vo));
    }
}
