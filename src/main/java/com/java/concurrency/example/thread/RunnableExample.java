package com.java.concurrency.example.thread;

/**
 * @Author: HB
 * @Description: 实现Runnable接口来创建线程
 * @CreateDate: 22:55 2020/10/24
 */

public class RunnableExample implements Runnable {

    private String name;

    public  RunnableExample(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            System.out.println(name + "运行" + i);
            try {
                Thread.sleep((int)Math.random() * 10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {

        new Thread(new RunnableExample("ThreadA")).start();

    }
}
