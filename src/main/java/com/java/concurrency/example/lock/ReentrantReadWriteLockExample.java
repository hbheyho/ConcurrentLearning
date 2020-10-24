package com.java.concurrency.example.lock;

import com.java.concurrency.annotations.NotThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @Author: HB
 * @Description: ReentrantReadWriteLock的使用
 * @CreateDate: 15:40 2020/10/5
 */
@Slf4j
@NotThreadSafe
public class ReentrantReadWriteLockExample {

    class Data {

    }
    private final Map<String, Data> map = new HashMap<>();
    // 声明一个ReentrantReadWriteLock
    private final static ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    // 分别定义一个读锁和写锁
    private final static Lock readLock = lock.readLock();
    private final static Lock writeLock = lock.writeLock();

    public Data get(String key) {
        // 读取时加上读锁
        readLock.lock();
        try{
            return map.get(key);
        }finally {
            readLock.unlock();
        }
    }

    public Set<String> getAllKeys(String key) {
        // 读取时加上读锁
        readLock.lock();
        try{
            return map.keySet();
        }finally {
            readLock.unlock();
        }
    }
    public Data put(String key, Data data) {
        // 写入时加上读锁
        writeLock.lock();
        try{
            return map.put(key, data);
        }finally {
            readLock.unlock();
        }
    }
}
