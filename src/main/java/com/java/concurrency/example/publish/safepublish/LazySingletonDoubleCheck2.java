package com.java.concurrency.example.publish.safepublish;

import com.java.concurrency.annotations.NotThreadSafe;
import com.java.concurrency.annotations.ThreadSafe;

/**
 * @Author: HB
 * @Description: 懒汉单例模式 - 双重检验锁
 * @CreateDate: 15:35 2020/10/13
 */

@ThreadSafe
public class LazySingletonDoubleCheck2 {
    // 解决指令重排带来的线程不安全问题
    private volatile static LazySingletonDoubleCheck2 instance = null;

    // 私有构造函数
    private LazySingletonDoubleCheck2() {

    }

    // 静态工厂方法获得一个静态对象
    public static LazySingletonDoubleCheck2 getInstance() {
        if (instance == null) {
            synchronized (LazySingletonDoubleCheck2.class) {
                if (instance == null) {
                    instance = new LazySingletonDoubleCheck2();
                }
            }
        }
        return instance;
    }
}
