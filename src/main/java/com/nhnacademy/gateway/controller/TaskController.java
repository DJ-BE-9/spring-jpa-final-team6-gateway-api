package com.nhnacademy.gateway.controller;

import com.nhnacademy.gateway.common.adaptor.login.UserNameAdaptor;
import com.nhnacademy.gateway.model.dto.MemberIdRequest;
import com.nhnacademy.gateway.model.dto.ResponseUserNameDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/task/{memberId}")
public class TaskController {

    @Autowired
    private UserNameAdaptor userNameAdaptor;

    @GetMapping
    public String getTask(Model model,
                          @PathVariable("memberId") String memberId) {
        ResponseUserNameDto response = userNameAdaptor.sendResponseUserNameDto(new MemberIdRequest(memberId));

        model.addAttribute("memberName", response.getUserName());

        return "taskMainForm";
    }

}
