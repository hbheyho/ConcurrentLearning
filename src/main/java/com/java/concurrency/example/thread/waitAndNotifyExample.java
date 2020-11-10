package com.java.concurrency.example.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

/**
 * @Author: HB
 * @Description: wait, notify实例
 * @CreateDate: 22:55 2020/10/24
 */

public class waitAndNotifyExample implements Runnable {

    private String name;
    private Object pre;
    private Object self;

    public waitAndNotifyExample(String name, Object pre, Object self) {
        this.name = name;
        this.pre = pre;
        this.self = self;
    }

    @Override
    public void run() {
        int count = 10;
        while (count > 0) {
            synchronized (pre) {
                synchronized (self) {
                    System.out.print(name);
                    count--;
                    self.notify();
                }
                
                // 释放了自身锁
                try {
                    // 当前线程阻塞, 释放对于pre的锁
                    pre.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {

        Object a = new Object();
        Object b = new Object();
        Object c = new Object();

        new Thread(new waitAndNotifyExample("A",c, a)).start();
        Thread.sleep(100);
        new Thread(new waitAndNotifyExample("B",a, b)).start();
        Thread.sleep(100);
        new Thread(new waitAndNotifyExample("C",b, c)).start();
        Thread.sleep(100);

    }
}
