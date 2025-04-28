package com.nhnacademy.gateway.controller.project;

import com.nhnacademy.gateway.exception.ValidationFailedException;
import com.nhnacademy.gateway.model.domain.Project;
import com.nhnacademy.gateway.model.request.project.RegisterProjectMemberRequest;
import com.nhnacademy.gateway.model.request.project.RegisterProjectRequest;
import com.nhnacademy.gateway.service.ProjectService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/project/register")
@Slf4j
public class ProjectRegisterController {

    @Autowired
    private ProjectService projectService;

    @GetMapping
    public String getProjectRegister(Model model,
                                     @RequestParam("memberId") String memberId) {
        log.error("{}",memberId);
        model.addAttribute("memberId", memberId);

        return "ProjectRegisterForm";
    }

    @PostMapping
    public String postProject(@RequestParam("memberId") String memberId,
                              @Validated @ModelAttribute RegisterProjectRequest projectRequest,
                              BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            throw new ValidationFailedException(bindingResult);
        }

        log.info("{}", memberId);
        projectService.postProject(projectRequest, memberId);
        return "redirect:/member?memberId=" + memberId;
    }

}
