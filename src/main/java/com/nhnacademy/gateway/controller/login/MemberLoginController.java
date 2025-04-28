package com.nhnacademy.gateway.controller.login;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/login")
public class MemberLoginController {

    @GetMapping
    public String getLogin(@RequestParam(value = "error", required = false) String error,
                           Model model) {

        if(error != null) {
            model.addAttribute("error", error);
        }

        return "memberLoginForm";
    }

}
