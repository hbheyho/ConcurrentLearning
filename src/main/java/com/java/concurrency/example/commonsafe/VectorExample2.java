package com.java.concurrency.example.commonsafe;

import com.java.concurrency.annotations.NotThreadSafe;
import com.java.concurrency.annotations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.Iterator;
import java.util.Vector;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * @Author: HB
 * @Description: 在遍历 Vector/ArrayList... 时尽量不要对其做删除以及添加操作
 * @CreateDate: 10:37 2020/10/15
 */
@Slf4j
@NotThreadSafe
public class VectorExample2 {

    // Exception in thread "main" java.util.ConcurrentModificationException
    public static void test1 (Vector<Integer> vector) {
        for (Integer i : vector) {
            if (i.equals(3))
                vector.remove(i);
        }
    }

    // Exception in thread "main" java.util.ConcurrentModificationException
    public static void test2 (Vector<Integer> vector) {
        Iterator<Integer> iterator = vector.iterator();
        while (iterator.hasNext()) {
            Integer i = iterator.next();
            if (i.equals(3))
                vector.remove(i);
        }
    }

    // 正确操作
    public static void test3 (Vector<Integer> vector) {
        Iterator<Integer> iterator = vector.iterator();
        while (iterator.hasNext()) {
            Integer i = iterator.next();
            if (i.equals(3))
                iterator.remove();
        }
    }

    // 正确操作
    public static void test4 (Vector<Integer> vector) {
        for (int i = 0; i < vector.size(); i++) {
            if (vector.get(i).equals(3))
                vector.remove(i);
        }
    }
    
    public static void main(String[] args) throws InterruptedException {
        Vector<Integer> vector = new Vector<>();
        vector.add(1);
        vector.add(2);
        vector.add(3);
        // test1(vector);
        // test2(vector);
        test3(vector);
        test4(vector);

    }


}
