package com.java.concurrency.example.sync;

import com.java.concurrency.annotations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

/**
 * @Author: HB
 * @Description: Synchronized 作用于静态方法
 * @CreateDate: 16:06 2020/10/6
 */
@ThreadSafe
@Slf4j
public class SynchronizedExample2 {
    // 共享资源
    static int i = 0;

    // synchronized作用于静态方法, 线程所得到的锁为类锁
    public static synchronized void increase() {
        i++;
    }

    public static void main(String[] args) throws InterruptedException {
        SynchronizedExample2 instance1 = new SynchronizedExample2();
        SynchronizedExample2 instance2 = new SynchronizedExample2();

        Thread thread1 = new Thread(() ->{
           for(int i = 0; i < 1000; i++) {
               instance1.increase();
           }
        });
        Thread thread2 = new Thread(() ->{
            for(int i = 0; i < 1000; i++) {
                instance2.increase();
            }
        });
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();

        log.info("count: {}", i);
    }

}
