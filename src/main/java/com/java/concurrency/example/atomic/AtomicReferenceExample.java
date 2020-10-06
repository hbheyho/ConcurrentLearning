package com.java.concurrency.example.atomic;

import com.java.concurrency.annotations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @Author: HB
 * @Description: AtomicReference 类的使用
 * @CreateDate: 14:34 2020/10/6
 */

@Slf4j
@ThreadSafe
public class AtomicReferenceExample {

    private static AtomicReference<Integer> atomicReference = new AtomicReference<>(0);

    public static void main(String[] args) {
        atomicReference.compareAndSet(0,2); // 2
        atomicReference.compareAndSet(0,1); // 2
        atomicReference.compareAndSet(1,3); // 2
        atomicReference.compareAndSet(2,4); // 4
        atomicReference.compareAndSet(3,5); // 4
        log.info("count:{}", atomicReference.get());
    }
}
