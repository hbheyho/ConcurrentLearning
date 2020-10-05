package com.java.concurrency.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Author: HB
 * @Description: 注解类 => 用来标注更加推荐的写法
 * @CreateDate: 10:47 2020/10/5
 */
// Target => 注解作用的目标. ElementType.TYPE => 类, 代表注解作用在Class, enum, interface 上
@Target(ElementType.TYPE)
// Retention => 注解存在的范围, RetentionPolicy.SOURCE => 在编译的时候会被忽略. 除此之外, 还有CLASS, RUNTIME
@Retention(RetentionPolicy.SOURCE)
public @interface Recommend {
    // 注解默认值为 "", 可通过 @ThreadSafe(value="xx") 设置注解值
    String value() default "";
}
