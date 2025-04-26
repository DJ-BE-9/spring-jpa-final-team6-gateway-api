package com.nhnacademy.gateway.controller.login;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login")
public class MemberLoginController {

    @GetMapping
    public String getLogin() {
        return "loginForm";
    }

}
