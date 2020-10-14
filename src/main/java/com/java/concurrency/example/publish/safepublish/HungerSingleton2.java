package com.java.concurrency.example.publish.safepublish;

import com.java.concurrency.annotations.ThreadSafe;

/**
 * @Author: HB
 * @Description: 饿汉单例模式 - 通过静态块来实现饿汉模式
 * @CreateDate: 15:35 2020/10/13
 */

@ThreadSafe
public class HungerSingleton2 {
    private static HungerSingleton2 instance = null;

    static {
        instance = new HungerSingleton2();
    }

    // 私有构造函数
    private HungerSingleton2() {

    }

    // 静态工厂方法获得一个静态对象
    public static HungerSingleton2 getInstance() {
        return instance;
    }
}
