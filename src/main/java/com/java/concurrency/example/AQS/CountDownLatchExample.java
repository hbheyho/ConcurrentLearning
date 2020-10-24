package com.java.concurrency.example.AQS;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @Author: HB
 * @Description: CountDownLatchExample
 * @CreateDate: 16:27 2020/10/16
 */

@Slf4j
public class CountDownLatchExample {
    private final static int threadCount = 200;

    public static void main(String[] args) throws InterruptedException {
        // 创建一个线程池
        ExecutorService executorService = Executors.newCachedThreadPool();
        final CountDownLatch countDownLatch = new CountDownLatch(threadCount);
        for (int i = 0; i < threadCount; i++) {
            final int threadNum = i;
            executorService.execute(() ->{
                try {
                    test(threadNum);
                } catch (Exception e){
                    log.info("{}", e);
                }finally {
                    countDownLatch.countDown();
                }
            });
        }

        // 只有当threadCount 为0时, 即200个线程执行完毕之后, 才会执行后面的语句
        countDownLatch.await();
        // 等待10ms, 若200个线程没有执行完毕, 则当前线程会继续执行
        // countDownLatch.await(10, TimeUnit.MILLISECONDS);
        log.info("finish");
        executorService.shutdown();
    }

    public static void test (int threadNum) throws InterruptedException {
        Thread.sleep(100);
        log.info("{}", threadNum);
        Thread.sleep(100);
    }
}
