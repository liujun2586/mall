package cn.wolfcode;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 定时器配置类
 */
@SpringBootApplication
@EnableScheduling
@PropertySource({"classpath:redis.properties","classpath:application.properties"})
public class SchedulerLauncher {

    //获取锁对象
    private static final ReentrantLock LOCK = new ReentrantLock();
    //获取锁监视器
    private static final Condition STOP = LOCK.newCondition();
    //日志对象
    private static final Logger logger= LoggerFactory.getLogger(SchedulerLauncher.class);

    public static void main(String[] args) {
        ConfigurableApplicationContext ctx = SpringApplication.run(SchedulerLauncher.class, args);
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
