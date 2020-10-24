package com.java.concurrency.example.AQS;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author: HB
 * @Description: CyclicBarrierExample
 * @CreateDate: 17:29 2020/10/16
 */
@Slf4j
public class CyclicBarrierExample {

    // 设置屏障数量为5
    private static CyclicBarrier cyclicBarrier = new CyclicBarrier(5);
    // 到达指定屏障之后, 优先执行barrierAction中的动作
    /*private static CyclicBarrier cyclicBarrier = new CyclicBarrier(5, () -> {
        log.info("Priority Action!!");
    });
*/
    public static void main(String[] args) throws InterruptedException {

        ExecutorService executorService = Executors.newCachedThreadPool();

        for (int i = 0; i < 10; i++) {
            final int threadNum = i;
            Thread.sleep(1000);
            executorService.execute(() -> {
                try {
                    doSomething(threadNum);
                } catch (InterruptedException | BrokenBarrierException e) {
                    e.printStackTrace();
                }
            });

        }
        executorService.shutdown();
    }

    private static void doSomething(int threadNum) throws InterruptedException, BrokenBarrierException {
        Thread.sleep(1000);
        log.info("{}: I am ready!", threadNum);
        // 某个线程初始化完毕, 则阻塞等待, 若阻塞数量达到5个, 则一起执行每个线程后面的方法
        cyclicBarrier.await();
        log.info("{}: I am done!", threadNum);
    }
}
