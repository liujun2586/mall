package cn.wolfcode.shop;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 操作目录相关的客户端
 */
@SpringBootApplication
public class ShopGoodsClientLauncher {

    public static void main(String[] args) {
        SpringApplication.run(ShopGoodsClientLauncher.class, args);
    }
}
