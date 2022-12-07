package com.luftmensch;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
//@EnableGlobalMethodSecurity(securedEnabled = true)
public class LuftmenschApplication {

    public static void main(String[] args) {
        SpringApplication.run(LuftmenschApplication.class, args);
        System.out.println("启动成功");
    }
}
