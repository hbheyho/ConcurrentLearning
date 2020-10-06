package com.java.concurrency.example.vola;

import com.java.concurrency.annotations.NotThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * @Author: HB
 * @Description: Volatile 关键词使用
 * @CreateDate: 15:40 2020/10/5
 */
@Slf4j
@NotThreadSafe
public class VolatileCount {
    // 请求数
    public static int clientTotal = 1000;
    // 并发请求数
    public static int threadTotal = 50;
    // 共享计数量
    public static volatile int count = 0;

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
        // count虽然被volatile修饰, 保证了可见性, 但是任然不是线程安全的. 因为count++不是原子操作
        // 1. 去主存中count最新值
        // 2. count + 1
        // 3. 最新值刷新到主存
        // 要正确使用volatile, 需要满足两个条件: 1. 对变量的写操作不依赖于当前值; 2. 该变量没有包含在其他变量不必要的式子中
        // 所以volatile很适合做为状态标识量.
        count++;
    }
}
