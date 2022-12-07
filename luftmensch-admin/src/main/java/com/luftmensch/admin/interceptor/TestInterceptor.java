package com.luftmensch.admin.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TestInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("进入了拦截器方法");
        System.out.println(request.getRequestURI());
        //假设获取了用户信息，用户id为管理员权限
        if (true) {
            request.getRequestDispatcher("/pre/01").forward(request, response);
            return false;
        }
        return true;
    }
}
