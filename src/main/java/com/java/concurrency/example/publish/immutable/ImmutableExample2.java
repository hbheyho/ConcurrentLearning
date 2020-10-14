package com.java.concurrency.example.publish.immutable;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.java.concurrency.annotations.NotThreadSafe;
import com.java.concurrency.annotations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: HB
 * @Description: 不可变对象实例
 * @CreateDate: 17:37 2020/10/14
 */

@Slf4j
@ThreadSafe
public class ImmutableExample2 {

    private final static ImmutableList immutableList = ImmutableList.of(1,2,3);
    private final static List immutableList2 = ImmutableList.of(1,2,3);

    private final static ImmutableSet immutableSet = ImmutableSet.copyOf(immutableList);

    private final static ImmutableMap<Integer, Integer> immutableMap = ImmutableMap.of(1,2,3,4,5,6);
    private final static ImmutableMap<Integer, Integer> immutableMap2 = ImmutableMap.<Integer, Integer>builder().put(1,2).put(3,4).build();

    public static void main(String[] args) {
        // ImmutableList 不可变, UnsupportedOperationException异常
        immutableList.add(4);
        // ImmutableMap 不可变, UnsupportedOperationException异常
        immutableMap.put(8,9);
    }

}
