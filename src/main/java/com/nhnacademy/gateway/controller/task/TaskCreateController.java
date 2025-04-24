package com.nhnacademy.gateway.controller.task;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/task/create")
public class TaskCreateController {

    @GetMapping("/{memberId}")
    public String getTaskCreate(Model model,
                                @PathVariable("memberId") String memberId) {

        model.addAttribute("memberId", memberId);

        return "taskCreateForm";
    }

}
