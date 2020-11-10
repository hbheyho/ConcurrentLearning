package com.java.concurrency.example.thread;

/**
 * @Author: HB
 * @Description: join 方法实例
 * @CreateDate: 22:55 2020/10/24
 */

public class JoinExample implements Runnable {
    private String name;

    public JoinExample(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + "线程开始运行");
        for (int i = 0; i < 5; i++) {
            System.out.println(Thread.currentThread().getName() + "运行" + i);
            try {
                Thread.sleep((int)Math.random() * 10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(Thread.currentThread().getName() + "线程结束运行");
    }

    public static void main(String[] args) throws InterruptedException {
        System.out.println(Thread.currentThread().getName() + "主线程开始运行");
        Thread thread1 = new Thread(new JoinExample("ThreadA"));
        Thread thread2 = new Thread(new JoinExample("ThreadB"));
        // 设置线程运行优先级
        thread1.setPriority(Thread.MAX_PRIORITY);
        thread1.start();

        // 暂停主线程, 等待thread1线程执行完毕
        thread1.join();
        thread2.start();
        // 暂停主线程, 等待thread2线程执行完毕
        thread2.join();

        System.out.println(Thread.currentThread().getName() + "主线程结束运行");
    }
}
