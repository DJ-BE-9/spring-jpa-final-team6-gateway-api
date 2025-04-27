package com.nhnacademy.gateway.controller.project;

import com.nhnacademy.gateway.model.request.project.RegisterProjectRequest;
import com.nhnacademy.gateway.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/member/{memberId}/project/register")
public class ProjectRegisterController {

    @Autowired
    private ProjectService projectService;

    @GetMapping
    public String getProjectRegister(Model model,
                                     @PathVariable("memberId") String memberId) {

        model.addAttribute("memberId", memberId);

        return "projectRegisterForm";
    }

    @PostMapping
    public String postProject(@PathVariable("memberId") String memberId,
                              @ModelAttribute RegisterProjectRequest projectRequest) {

        projectService.postProject(projectRequest);

        return "redirect:/member/" + memberId + "/project/register";
    }

}
