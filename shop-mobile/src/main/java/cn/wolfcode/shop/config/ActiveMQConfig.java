package cn.wolfcode.shop.config;

import cn.wolfcode.shop.util.ConstantUtil;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.jms.Destination;


@Configuration
public class ActiveMQConfig {

    /**
     * 点对点的模型
     * @return
     */
    @Bean
    public Destination generateOrderDestinationQueue(){
        return new ActiveMQQueue(ConstantUtil.GENERATE_ORDER_MESSAGE);
    }
}
