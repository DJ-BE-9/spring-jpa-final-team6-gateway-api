package com.nhnacademy.gateway.controller.project;

import com.nhnacademy.gateway.model.request.project.RegisterProjectRequest;
import com.nhnacademy.gateway.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/project/{memberId}/register")
public class ProjectRegisterController {

    @Autowired
    private TaskService taskService;

    @GetMapping
    public String getProjectRegister(Model model,
                                     @PathVariable("memberId") String memberId) {

        model.addAttribute("memberId", memberId);

        return "taskRegisterForm";
    }

    @PostMapping
    public String postProject(@PathVariable("memberId") String memberId,
                              @ModelAttribute RegisterProjectRequest projectRequest) {

        taskService.postProject(projectRequest);

        return "redirect:/task/" + memberId;
    }

}
