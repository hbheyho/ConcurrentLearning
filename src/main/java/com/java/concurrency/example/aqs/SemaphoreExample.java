package com.java.concurrency.example.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * @Author: HB
 * @Description: SemaphoreExample
 * @CreateDate: 16:27 2020/10/16
 */

@Slf4j
public class SemaphoreExample {
    private final static int threadCount = 200;

    public static void main(String[] args) throws InterruptedException {
        // 创建一个线程池
        ExecutorService executorService = Executors.newCachedThreadPool();
        // 创建一个值为10的信号量
        final Semaphore semaphore = new Semaphore(20);
        for (int i = 0; i < threadCount; i++) {
            final int threadNum = i;
            executorService.execute(() ->{
                try {
                    // 1. 获取一个许可
                    semaphore.acquire();
                    // 2. 一次性可获取三个许可
                    // semaphore.acquire(3);
                    // test(threadNum);
                    // 释放一个需求
                    // semaphore.release();
                    // 3. 尝试获取许可, 获取不到许可的线程被丢弃
                    if (semaphore.tryAcquire()) {
                        test(threadNum);
                        // 释放一个需求
                        semaphore.release();
                    }
                } catch (Exception e){
                    log.info("{}", e);
                }
            });
        }
        executorService.shutdown();
    }

    public static void test (int threadNum) throws InterruptedException {
        log.info("{}", threadNum);
        Thread.sleep(1000);
    }
}
