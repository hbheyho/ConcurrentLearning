package com.java.concurrency.example.atomic;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

/**
 * @Author: HB
 * @Description: AtomicReferenceFieldUpdater类的使用
 * @CreateDate: 14:42 2020/10/6
 */

public class AtomicReferenceFieldUpdaterExample {

    static class User {
        // 要原子修改的字段必须被volatile修饰
        public volatile String name = "HB";
        public Integer age = 23;
    }

    // 相比于AtomicIntegerFieldUpdater,  AtomicReferenceFieldUpdater还需要指定字段的类型, 例如下面的String.class
    private static AtomicReferenceFieldUpdater<User, String> updater = AtomicReferenceFieldUpdater.
            newUpdater(User.class, String.class, "name");


    public static void main(String[] args) {
        User user = new User();
        if (updater.compareAndSet(user, "HB", "QQL")) {
            System.out.println(user.name);
        }
    }

}
