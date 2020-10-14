package com.java.concurrency.example.publish.unsafepublish;

import com.java.concurrency.annotations.NotThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;

/**
 * @Author: HB
 * @Description: 不安全发布对象 - 也可以被看出为对象逸出
 * @CreateDate: 22:19 2020/10/12
 */

@Slf4j
@NotThreadSafe
public class UnsafePublish {
    // 定义一个私有的字符串对象数组
    private String[] strings = {"HB", "QQL", "HY"};

    // 通过一个公有方法发布它, 使得当前范围之外代码所使用
    public String[] getStrings () {
        return strings;
    }

    public static void main(String[] args) {
        UnsafePublish unsafePublish = new UnsafePublish();
        log.info("Stings: {}", Arrays.toString(unsafePublish.getStrings()));
        unsafePublish.getStrings()[0] = "CCZ";
        // 不安全, 不能保证是否会有其他线程会够对私有对象进行修改
        log.info("Stings: {}", Arrays.toString(unsafePublish.getStrings()));
    }
}
