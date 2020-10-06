package com.java.concurrency.example.sync;

import com.java.concurrency.annotations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

/**
 * @Author: HB
 * @Description: Synchronized 作用于代码块 - 对实例对象加锁/对类加锁
 * @CreateDate: 16:06 2020/10/6
 */
@ThreadSafe
@Slf4j
public class SynchronizedExample3 {
    // 共享资源
    static int i = 0;

    // synchronized作用于代码块, 同步作用域为代码块的作用域, 锁对象为调用该方法的实例
    public void increase() {

        synchronized (this){
            i++;
        }

        /*// 对类加锁
        synchronized (SynchronizedExample3.class){
            i++;
        }*/
    }

    public static void main(String[] args) throws InterruptedException {
        SynchronizedExample3 instance1 = new SynchronizedExample3();
        // SynchronizedExample3 instance2 = new SynchronizedExample3();
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
