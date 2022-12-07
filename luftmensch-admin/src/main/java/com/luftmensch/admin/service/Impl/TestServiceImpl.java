package com.luftmensch.admin.service.Impl;

import com.luftmensch.admin.service.TestService;
import org.springframework.stereotype.Service;

@Service
public class TestServiceImpl implements TestService {


    @Override
    public String testMethod() {
        return "请求未被拦截";
    }

    @Override
    public String interceptorMethod() {
        return "请求被拦截了";
    }
}
