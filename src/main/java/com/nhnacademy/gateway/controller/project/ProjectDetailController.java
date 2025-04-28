package com.nhnacademy.gateway.controller.project;

import com.nhnacademy.gateway.model.domain.Project;
import com.nhnacademy.gateway.model.dto.task.ResponseTaskDetailDto;
import com.nhnacademy.gateway.service.ProjectService;
import com.nhnacademy.gateway.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/project/{projectId}")
public class ProjectDetailController {

    @Autowired
    private ProjectService projectService;
    // TODO Task 목록 보여주기
    @Autowired
    private TaskService taskService;

    @GetMapping
    public String getProjectDetail(@PathVariable("projectId") long projectId, Model model) {
        Project project = projectService.getProjectByProjectId(projectId);
        model.addAttribute("projectTitle", project.getProjectName());
        model.addAttribute("projectState", project.getProjectState());

        List<ResponseTaskDetailDto> taskList= taskService.getTaskByProjectId(projectId);
        model.addAttribute("taskList", taskList);

        return "projectDetailForm";
    }

    @PostMapping("/members")
    public String postProjectRegisterMember(@PathVariable("projectId") long projectId,
                                            @RequestParam("memberId") String memberId) {

        projectService.registerMember(projectId, memberId);

        return "redirect:/project/" + projectId;
    }

}
