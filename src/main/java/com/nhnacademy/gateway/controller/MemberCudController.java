package com.nhnacademy.gateway.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MemberCudController {

    @PostMapping("/account/{memberId}")
    public String postMemberCud(@PathVariable("memberId") String memberId) {



        return null;
    }

}
