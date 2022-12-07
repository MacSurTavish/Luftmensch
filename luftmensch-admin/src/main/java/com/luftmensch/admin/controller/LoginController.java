package com.luftmensch.admin.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;

@Tag(name = "用户登录")
@CrossOrigin
@Controller
public class LoginController {

    /**
     * 登录
     * @return
     */
    @PostMapping("/toMain")
    public String toMain() {
        return "redirect:main.html";
    }

    @PostMapping("/toError")
    public String toError() {
        return "redirect:error.html";
    }
}
