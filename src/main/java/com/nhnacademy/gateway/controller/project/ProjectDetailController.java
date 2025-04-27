package com.nhnacademy.gateway.controller.project;

import com.nhnacademy.gateway.model.domain.Project;
import com.nhnacademy.gateway.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/project/{projectId}")
public class ProjectDetailController {

    @Autowired
    private ProjectService projectService;

    @GetMapping
    public String getProjectDetail(@PathVariable("projectId") long projectId, Model model) {
        Project project = projectService.getProjectByProjectId(projectId);
        model.addAttribute("projectTitle", project.getProjectName());
        model.addAttribute("projectState", project.getProjectState());

        return "projectDetailForm";
    }

}
