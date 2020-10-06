package com.java.concurrency.example.atomic;

import com.java.concurrency.annotations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * @Author: HB
 * @Description: AtomicStampedReference类的使用 - AtomicStampedReference 和 AtomicInteger的比较使用
 * @CreateDate: 15:03 2020/10/6
 */
@ThreadSafe
@Slf4j
public class AtomicStampedReferenceExample {
    // 初始值为100
    private static AtomicInteger atomicInteger = new AtomicInteger(100);
    // 初始值为100, 初始版本号为0
    private static AtomicStampedReference<Integer> stampedReference = new AtomicStampedReference(100, 0);

    public static void main(String[] args) throws InterruptedException {

        /*===================================AtomicInteger===================================*/

        Thread atomicIntegerThread1 = new Thread(() ->{
            // 将值修改为101后, 重新修改为100
            atomicInteger.compareAndSet(100, 101);
            atomicInteger.compareAndSet(101, 100);
        });

        Thread atomicIntegerThread2 = new Thread(() ->{
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // 试图将100修改为101
            boolean isSuccess = atomicInteger.compareAndSet(100, 101);
            log.info("AtomicInteger?:{}", isSuccess);
        });

        atomicIntegerThread1.start();
        atomicIntegerThread2.start();
        atomicIntegerThread1.join();
        atomicIntegerThread2.join();

        /*===================================AtomicStampedReference===================================*/

        Thread stampedReferenceThread1 = new Thread(() ->{
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // 将值修改为101后, 重新修改为100, 同时版本号 + 1
            stampedReference.compareAndSet(100, 101, stampedReference.getStamp(), stampedReference.getStamp() + 1);
            stampedReference.compareAndSet(101, 100, stampedReference.getStamp(), stampedReference.getStamp() + 2);
        });

        Thread stampedReferenceThread2 = new Thread(() ->{
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // 试图将100修改为101
            boolean isSuccess  = stampedReference.compareAndSet(100, 101, 0, stampedReference.getStamp() + 1);
            log.info("AtomicStampedReference?:{}", isSuccess);
        });
        stampedReferenceThread1.start();
        stampedReferenceThread2.start();
        stampedReferenceThread1.join();
        stampedReferenceThread2.join();

    }

}
