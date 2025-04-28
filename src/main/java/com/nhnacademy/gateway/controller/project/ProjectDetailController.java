package com.nhnacademy.gateway.controller.project;

import com.nhnacademy.gateway.model.domain.Project;
import com.nhnacademy.gateway.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/project/{projectId}")
public class ProjectDetailController {

    @Autowired
    private ProjectService projectService;

    @GetMapping
    public String getProjectDetail(@PathVariable("projectId") long projectId, Model model) {
        Project project = projectService.getProjectByProjectId(projectId);
//        model.addAttribute("project", project.getProjectId());
//        model.addAttribute("projectId", projectId);
        model.addAttribute("projectTitle", project.getProjectName());
        model.addAttribute("projectState", project.getProjectState());

        return "projectDetailForm";
    }

    @PostMapping("/members")
    public String postProjectRegisterMember(@PathVariable("projectId") long projectId,
                                            @RequestParam("memberId") String memberId) {

        projectService.registerMember(projectId, memberId);

        return "redirect:/project/" + projectId;
    }

}
