package com.luftmensch.admin.controller;

import com.luftmensch.admin.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pre")
public class preController {

    @Autowired
    private TestService testService;

    @GetMapping("/01")
    public String pre() {
        return testService.interceptorMethod();
    }
}
