package com.luftmensch.project.service;

import com.luftmensch.project.bean.TestServiceProperties;
import org.springframework.beans.factory.annotation.Autowired;

public class TestService {

    @Autowired
    private TestServiceProperties testServiceProperties;

    public void testAutoConfiguration(int num) {
        System.out.println(testServiceProperties.getPre());
        System.out.println("测试收到的数据为：" + num );
        System.out.println(testServiceProperties.getAfter());

    }
}
