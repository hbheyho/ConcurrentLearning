package com.java.concurrency.example.forkjoin;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
import java.util.concurrent.RecursiveTask;

/**
 * @Author: HB
 * @Description: fork/join 框架实例
 * @CreateDate: 20:47 2020/10/26
 */

public class ForkJoinExample extends RecursiveTask<Integer> {

    public static final int threshold = 2;
    private Integer start;
    private Integer end;

    ForkJoinExample(Integer start, Integer end) {
        this.start = start;
        this.end = end;
    }


    /**
     * @Author: HB
     * @Description: 重写计算任务
     * @Date: 20:48 2020/10/26
     * @Params: null
     * @Returns:
    */
    @Override
    protected Integer compute() {
        int sum = 0;
        // 如果任务足够小就直接计算任务
        boolean canCompute = (end - start) <= threshold;
        if (canCompute) {
            for (int i = start; i <= end; i++) {
                sum += i;
            }
        }
        // 对任务进行拆分
        else {
            int middle = (start + end) / 2;
            // 拆分子任务
            ForkJoinExample leftTask = new ForkJoinExample(start, middle);
            ForkJoinExample rightTask = new ForkJoinExample(middle + 1, end);

            // 执行子任务
            leftTask.fork();
            rightTask.fork();

            // 等待两个任务执行并合并结果
            int leftResult = leftTask.join();
            int rightResult = rightTask.join();

            sum = leftResult + rightResult;
        }
        return sum;
    }


    public static void main(String[] args) {
        ForkJoinPool forkJoinPool = new ForkJoinPool();

        // 新建一个ForkJoin任务, 计算 1...100
        ForkJoinExample forkJoinExample = new ForkJoinExample(1, 100);

        // 执行一个任务
        Future<Integer> result = forkJoinPool.submit(forkJoinExample);

        try {
            System.out.println("Result:" + result.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
