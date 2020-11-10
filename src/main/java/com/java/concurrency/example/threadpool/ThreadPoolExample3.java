package com.java.concurrency.example.threadpool;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author: HB
 * @Description: newSingleThreadExecutor 线程池实例
 * @CreateDate: 21:45 2020/10/26
 */

@Slf4j
public class ThreadPoolExample3 {
    public static void main(String[] args) {
        // 声明一个线程池
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        for (int i = 0; i < 10; i++) {
            final int index = i;
            // 往线程池中添加任务
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    log.info("task:{}", index);
                }
            });
        }
        executorService.shutdown();

    }
}
