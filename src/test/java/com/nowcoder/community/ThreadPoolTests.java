package com.nowcoder.community;

import com.nowcoder.community.service.AlphaService;
import com.nowcoder.community.service.AlphaServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.concurrent.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = CommunityApplication.class)
/**
 * @author VvV
 * @date 2022/10/12
 */
public class ThreadPoolTests {

    private static final Logger logger = LoggerFactory.getLogger(ThreadPoolTests.class);

    // JDK普通线程池
    private ExecutorService executorService = Executors.newFixedThreadPool(8);

    // JDk可执行定时任务的线程池
    private ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(8);

    // spring 普通线程池
    @Autowired
    private ThreadPoolTaskExecutor taskExecutor;

    // spring 可执行定时任务的线程池
    @Autowired
    private ThreadPoolTaskScheduler taskScheduler;

    @Autowired
    private AlphaService alphaService;

    public void sleep(long millis){
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    // JDK普通线程池
    @Test
    public void testExecutorService(){
        Runnable task = () -> {
            logger.debug("hello executorService.");
        };

        for (int i = 0; i < 10; i++) {
            executorService.submit(task);
        }

        sleep(10000);
    }

    // JDk可执行定时任务的线程池
    @Test
    public void testScheduledExecutorService(){
        Runnable task = () -> {
          logger.debug("hello scheduledExecutorService.");
        };

        scheduledExecutorService.scheduleAtFixedRate(task, 2000, 5000, TimeUnit.MICROSECONDS);

        sleep(10000);
    }

    // spring 普通线程池
    @Test
    public void testThreadPoolTaskExecutor(){
        Runnable task = new Runnable() {
            @Override
            public void run() {
                logger.debug("hello springThreadPoolTaskExecutor.");
            }
        };

        for (int i = 0; i < 10; i++) {
            taskExecutor.submit(task);
        }

    }

    // spring 可执行定时任务的线程池
    @Test
    public void testThreadPoolTaskScheduler(){
        Runnable task = new Runnable() {
            @Override
            public void run() {
                logger.debug("hello springThreadPoolTaskScheduler.");
            }
        };

        // 开始时间是执行开始10秒后
        Date startTime = new Date(System.currentTimeMillis() + 10000);
        taskScheduler.scheduleAtFixedRate(task, startTime, 10000);

        sleep(30000);
    }

    // spring 普通线程池(简化)
    @Test
    public void testThreadPoolTaskExecutorSimple(){
        for (int i = 0; i < 10; i++) {
            alphaService.execute1();
        }

        sleep(10000);
    }

    // spring 可执行定时任务的线程池 注解方式自动启动 alphaService的execute2
    @Test
    public void testThreadPoolTaskSchedulerSimple(){

        sleep(30000);
    }


}
