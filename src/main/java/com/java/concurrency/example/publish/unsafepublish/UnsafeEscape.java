package com.java.concurrency.example.publish.unsafepublish;

import com.java.concurrency.annotations.NotRecommend;
import com.java.concurrency.annotations.NotThreadSafe;
import lombok.extern.slf4j.Slf4j;

/**
 * @Author: HB
 * @Description: 对象溢出
 * @CreateDate: 22:34 2020/10/12
 */

@NotThreadSafe
@NotRecommend
@Slf4j
public class UnsafeEscape {
    private int thisCanBeEscape = 89;

    public UnsafeEscape () throws InterruptedException {

        // a. 创建一个线程
        Thread thread = new Thread(() ->{
            log.info("{}", UnsafeEscape.this.thisCanBeEscape);
        });
        thread.run();

        // b. 调用一个实例方法
        doSomething();

        // 进入静态内部类完成静态内部类的初始化, 本身还没构造完成
        new innerClass();
    }

    public void doSomething() {
        log.info("{}", UnsafeEscape.this.thisCanBeEscape);
    }

    // 定义一个内部类
    private class innerClass {
        public innerClass () {
            // 一种错误的发布. 当一个对象还没有构造完成时，就使它被其他线程所见.
            // 内部类持有外部类的this引用, 可通过OuterClass.this.properties引用
            log.info("{}", UnsafeEscape.this.thisCanBeEscape);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        UnsafeEscape unsafeEscape = new UnsafeEscape();
    }
}
