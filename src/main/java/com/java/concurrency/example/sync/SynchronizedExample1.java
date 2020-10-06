package com.java.concurrency.example.sync;

import com.java.concurrency.annotations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

/**
 * @Author: HB
 * @Description: Synchronized 作用于实例方法
 * @CreateDate: 16:06 2020/10/6
 */
@ThreadSafe
@Slf4j
public class SynchronizedExample1 {
    // 共享资源
    static int i = 0;

    // synchronized 作用于实例方法
    public synchronized void increase() {
        i++;
    }

    public static void main(String[] args) throws InterruptedException {
        SynchronizedExample1 instance1 = new SynchronizedExample1();
        // 当synchronized 作用于不同的实例对象时, 会获取到不用的锁, 所以不能保证线程安全
        // SynchronizedExample1 instance2 = new SynchronizedExample1();
        Thread thread1 = new Thread(() ->{
           for(int i = 0; i < 1000; i++) {
               instance1.increase();
           }
        });
        Thread thread2 = new Thread(() ->{
            for(int i = 0; i < 1000; i++) {
                instance1.increase();
                // instance2.increase();
            }
        });
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();

        log.info("count: {}", i);
    }

}
