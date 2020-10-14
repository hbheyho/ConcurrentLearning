package com.java.concurrency.example.publish.safepublish;

import com.java.concurrency.annotations.NotThreadSafe;

/**
 * @Author: HB
 * @Description: 懒汉单例模式
 * @CreateDate: 15:35 2020/10/13
 */

@NotThreadSafe
public class LazySingleton {
    private static LazySingleton instance = null;

    // 私有构造函数
    private LazySingleton() {

    }

    // 静态工厂方法获得一个静态对象
    public static LazySingleton getInstance() {
        if (instance == null) {
            instance = new LazySingleton();
        }
        return instance;
    }
}
