package com.java.concurrency.example.thread;

/**
 * @Author: HB
 * @Description: 继承Thread类来创建线程
 * @CreateDate: 22:55 2020/10/24
 */

public class ThreadExample extends Thread {
    private String name;

    public ThreadExample(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            System.out.println(name + "运行" + i);
            try {
                sleep((int)Math.random() * 10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        ThreadExample thread1 = new ThreadExample("threadA");
        ThreadExample thread2 = new ThreadExample("threadB");
        thread1.start();
        thread2.start();
    }
}
