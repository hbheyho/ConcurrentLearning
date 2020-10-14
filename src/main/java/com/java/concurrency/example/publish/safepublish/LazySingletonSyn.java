package com.java.concurrency.example.publish.safepublish;

import com.java.concurrency.annotations.NotRecommend;
import com.java.concurrency.annotations.ThreadSafe;

/**
 * @Author: HB
 * @Description: 懒汉单例模式 - synchronized
 *               性能开销低
 * @CreateDate: 15:35 2020/10/13
 */

@ThreadSafe
@NotRecommend
public class LazySingletonSyn {
    private static LazySingletonSyn instance = null;

    // 私有构造函数
    private LazySingletonSyn() {

    }

    // 静态工厂方法获得一个静态对象
    public static synchronized LazySingletonSyn getInstance() {
        if (instance == null) {
            instance = new LazySingletonSyn();
        }
        return instance;
    }
}
