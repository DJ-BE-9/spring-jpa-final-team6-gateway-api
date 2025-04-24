package com.nhnacademy.gateway.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/task/create")
public class TaskCreateController {

    @GetMapping
    public String getTaskCreate() {
        return "taskCreateForm";
    }

}
