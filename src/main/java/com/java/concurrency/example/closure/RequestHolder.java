package com.java.concurrency.example.closure;

/**
 * @Author: HB
 * @Description: ThreadLocal 线程封闭
 * @CreateDate: 22:47 2020/10/14
 */

public class RequestHolder {
    private final static ThreadLocal<Long> requestHolder = new ThreadLocal<>();

    public static void setId (Long id) {
        requestHolder.set(id);
    }

    public static Long getId () {
        return requestHolder.get();
    }

    public static void remove () {
        requestHolder.remove();
    }

}
