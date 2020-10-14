package com.java.concurrency.example.publish.safepublish;

import com.java.concurrency.annotations.NotThreadSafe;
import com.java.concurrency.annotations.ThreadSafe;

/**
 * @Author: HB
 * @Description: 饿汉单例模式
 * @CreateDate: 15:35 2020/10/13
 */

@ThreadSafe
public class HungerSingleton {
    private static HungerSingleton instance = new HungerSingleton();

    // 私有构造函数
    private HungerSingleton() {

    }

    // 静态工厂方法获得一个静态对象
    public static HungerSingleton getInstance() {
        return instance;
    }
}
