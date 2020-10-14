package com.java.concurrency.example.publish.safepublish;

import com.java.concurrency.annotations.NotThreadSafe;

/**
 * @Author: HB
 * @Description: 懒汉单例模式 - 双重检验锁
 * @CreateDate: 15:35 2020/10/13
 */

@NotThreadSafe
public class LazySingletonDoubleCheck {
    private static LazySingletonDoubleCheck instance = null;

    // 私有构造函数
    private LazySingletonDoubleCheck() {

    }

    // 静态工厂方法获得一个静态对象
    public static LazySingletonDoubleCheck getInstance() {
        if (instance == null) {
            synchronized (LazySingletonDoubleCheck.class) {
                if (instance == null) {
                    // new 过程中的指令重排, 使得当前方法也不是线程安全的
                    instance = new LazySingletonDoubleCheck();
                }
            }
        }
        return instance;
    }
}
