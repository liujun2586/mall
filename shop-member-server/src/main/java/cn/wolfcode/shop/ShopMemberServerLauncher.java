package cn.wolfcode.shop;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement //开启事物扫描
@PropertySource({"classpath:redis.properties","classpath:application.properties"})
public class ShopMemberServerLauncher extends AbstractBaseServerLauncher{

    public static void main(String[] args) {
        new ShopMemberServerLauncher().start(args);
    }
}
