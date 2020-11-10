package com.java.concurrency.example.thread.callable;

import java.util.concurrent.*;

/**
 * @Author: HB
 * @Description: 使用Callable+Future获取执行结果
 * @CreateDate: 16:43 2020/10/26
 */

public class FutureExample {
    // 利用callable创建任务
    static class Task implements Callable<Integer> {

        @Override
        public Integer call() throws Exception {
            System.out.println("子线程正在进行计算");
            Thread.sleep(3000);
            int sum = 0;
            for (int i = 0; i < 1000; i++) {
                sum += i;
            }
            return sum;
        }
    }

    public static void main(String[] args) {
        // 创建线程池
        ExecutorService executorService = Executors.newCachedThreadPool();
        Task task = new Task();
        // Future 对执行结果进行操作
        Future<Integer> future = executorService.submit(task);
        executorService.shutdown();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("主线程正在执行任务");

        try {
            System.out.println("Task 运行结果" + future.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        System.out.println("主线程执行任务完毕");

    }
}
