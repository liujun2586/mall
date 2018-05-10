package cn.wolfcode.shop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

/**
 * 操作目录相关的客户端
 */
@SpringBootApplication
@PropertySource("classpath:activeMq.properties")
public class ShopMobileClientLauncher {

    public static void main(String[] args) {
        SpringApplication.run(ShopMobileClientLauncher.class, args);
    }
}
