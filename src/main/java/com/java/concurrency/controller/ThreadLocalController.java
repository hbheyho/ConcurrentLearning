package com.java.concurrency.controller;

import com.java.concurrency.example.closure.RequestHolder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: HB
 * @Description: ThreadLocal 验证
 * @CreateDate: 23:17 2020/10/14
 */

@RestController
@RequestMapping("/threadLocal")
@Slf4j
public class ThreadLocalController {
    @RequestMapping("/testing")
    public Long test() {
        return RequestHolder.getId();
    }
}
