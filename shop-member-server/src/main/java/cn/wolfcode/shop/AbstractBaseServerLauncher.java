package cn.wolfcode.shop;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.PropertySource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

@SpringBootApplication
@EnableTransactionManagement //开启事物扫描
@MapperScan("cn.wolfcode.shop.mapper") //开启mapper包扫描生成代理类
@PropertySource({"classpath:redis.properties","classpath:application.properties"})
abstract public class AbstractBaseServerLauncher {
    //获取锁对象
    private static final ReentrantLock LOCK = new ReentrantLock();
    //获取锁监视器
    private static final Condition STOP = LOCK.newCondition();
    //日志对象
    private static final Logger logger= LoggerFactory.getLogger(AbstractBaseServerLauncher.class);

    public void start(String[] args) {

        ConfigurableApplicationContext ctx = SpringApplication.run(this.getClass(), args);
        //使用锁防止容器运行后直接关闭
        Runtime.getRuntime().addShutdownHook(new Thread(()->{
            //关闭容器
            ctx.stop();
            //释放资源
            try {
                LOCK.lock();
                STOP.signal();
            }finally {
                LOCK.unlock();
            }
        }));
        //让线程进入等待
        try {
            LOCK.lock();
            STOP.await();
        }catch (Exception e){
            logger.error("container start fialed",e);
        }finally {
            LOCK.unlock();
        }
    }
}
