package com.java.concurrency.utils;

import com.java.concurrency.example.closure.RequestHolder;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @Author: HB
 * @Description: HttpFilter
 * @CreateDate: 22:57 2020/10/14
 */
@Slf4j
public class HttpFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        // 转为了 HttpServletRequest
        HttpServletRequest httpServletRequest = (HttpServletRequest)servletRequest;
        RequestHolder.setId(Thread.currentThread().getId());
        log.info("do filter:{}, {}", Thread.currentThread().getId(), httpServletRequest.getServletPath());
        // 让请求继续被处理
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
