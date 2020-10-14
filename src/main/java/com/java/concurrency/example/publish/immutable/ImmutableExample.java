package com.java.concurrency.example.publish.immutable;

import com.java.concurrency.annotations.NotThreadSafe;
import com.java.concurrency.annotations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: HB
 * @Description: 不可变对象实例
 * @CreateDate: 17:37 2020/10/14
 */

@Slf4j
// 使用Map 线程不安全
@NotThreadSafe
// 使用 Collections.unmodifiableMap() 线程安全
@ThreadSafe
public class ImmutableExample {
    private final static Integer a = 1;
    private final static String b = "2";
    private final static Map<Integer, Integer> map = new HashMap<>();
    private static Map<Integer, Integer> map2 = new HashMap<>();

    static {
        map.put(1,2);
        map.put(2,3);
        map.put(3,4);

        // Collections.unmodifiableMap(map2) 设置map为不可变 - 线程安全的
        map2.put(1,4);
        map2 = Collections.unmodifiableMap(map2);

    }

    public static void main(String[] args) {
        // finale 修饰引用类型, 则不可被修改为指向新的引用,但是可以对其指向的对象进行修改
        // map = new HashMap<>();
        // 可以对map的内容进行修改 ,会引用线程安全问题
        map.put(1,5);
        log.info("map:{}", map.get(1));

        map2.put(1,5);
        // UnsupportedOperationException 异常
        log.info("map2:{}", map2.get(1));
    }

}
