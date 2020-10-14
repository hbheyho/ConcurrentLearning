package com.java.concurrency.example.publish.safepublish;

import com.java.concurrency.annotations.Recommend;
import com.java.concurrency.annotations.ThreadSafe;

/**
 * @Author: HB
 * @Description: 枚举类实现单例模式
 * @CreateDate: 15:35 2020/10/13
 */

@ThreadSafe
@Recommend
public class EnumsSingleton {

    // 定义一个枚举类
    private enum Singleton {
        INSTANCE;
        private EnumsSingleton singleton;
        // JVM保证这个方法只会调用一次
        Singleton() {
            singleton = new EnumsSingleton();
        }

        public EnumsSingleton getSingleton() {
            return singleton;
        }
    }

    // 私有构造函数
    private EnumsSingleton() {

    }

    // 静态工厂方法获得一个静态对象
    public static EnumsSingleton getInstance() {
        return Singleton.INSTANCE.getSingleton();
    }
}
