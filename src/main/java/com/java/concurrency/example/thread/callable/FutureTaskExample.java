package com.java.concurrency.example.thread.callable;

import java.util.concurrent.*;

/**
 * @Author: HB
 * @Description: 使用Callable+FutureTask获取执行结果
 * @CreateDate: 16:43 2020/10/26
 */

public class FutureTaskExample {

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
        /*ExecutorService executorService = Executors.newCachedThreadPool();
        Task task = new Task();
        FutureTask<Integer> futureTask = new FutureTask<Integer>(task);
        executorService.submit(futureTask);
        executorService.shutdown();*/

        Task task = new Task();
        FutureTask<Integer> futureTask = new FutureTask<Integer>(task);
        // 因为futureTask实现了RunnableFuture接口, 而RunnableFuture接口继承了Runnable
        Thread thread = new Thread(futureTask);
        thread.start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("主线程正在执行任务");

        try {
            System.out.println("Task 运行结果" + futureTask.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        System.out.println("主线程执行任务完毕");

    }
}
