package com.java.concurrency.example.threadpool;

import lombok.extern.slf4j.Slf4j;

import javax.annotation.security.RunAs;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @Author: HB
 * @Description: newSingleThreadExecutor 线程池实例
 * @CreateDate: 21:45 2020/10/26
 */

@Slf4j
public class ThreadPoolExample4 {
    public static void main(String[] args) {
        // 声明一个线程池
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(5);

        // 延迟3s执行
        /*executorService.schedule(new Runnable() {
            @Override
            public void run() {
                log.info("Execute Thread.");
            }
        },3, TimeUnit.SECONDS);*/

        // 每次间隔3s执行 - 在执行期间不能调用 executorService.shutdown()方法
        /*executorService.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                log.info("Execute Thread.");
            }
        }, 1, 3, TimeUnit.SECONDS);*/

        // executorService.shutdown();

        // 利用Timer 做延迟执行
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                log.info("Execute Thread.");
            }
        }, new Date(), 5 * 1000);
    }
}
