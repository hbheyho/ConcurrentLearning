package com.java.concurrency.example.others;

import com.java.concurrency.annotations.NotThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

/**
 * @Author: HB
 * @Description: Semaphore 和 CountDownLatch的使用
 * @CreateDate: 15:40 2020/10/5
 */
@Slf4j
@NotThreadSafe
public class ConcurrencyCount {
    // 请求数
    public static int clientTotal = 1000;
    // 并发请求数
    public static int threadTotal = 50;
    // 共享计数量
    public static int count = 0;

    public static void main(String[] args) throws InterruptedException {
        // 定义线程池
        ExecutorService executorService = Executors.newCachedThreadPool();
        // 定义信号量, 并设置运行并发数
        final Semaphore semaphore = new Semaphore(threadTotal);
        // 定义CountDownLatch, 在所有的请求完成之后输出结果
        final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);

        // 进行执行
        for (int i = 0; i < clientTotal; i++) {
            executorService.execute(() -> {
                try {
                    // 获取信号量 - 最大并发数为50
                    semaphore.acquire();
                    count();
                    // 释放信号量
                    semaphore.release();
                } catch (InterruptedException e) {
                    log.info("InterruptedException", e);
                }
                // 进行count--
                countDownLatch.countDown();
            });
        }
        // 只有countDown到0的时候, 才会唤起线程继续执行, 不然会挂起继续等待
        countDownLatch.await();
        // 关闭线程池
        executorService.shutdown();
        // 在所有子线程执行完之后,打印count值
        log.info("count:{}", count);
    }

    private static void count() {
        count++;
    }
}
