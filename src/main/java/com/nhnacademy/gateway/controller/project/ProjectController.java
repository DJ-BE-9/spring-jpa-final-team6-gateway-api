package com.nhnacademy.gateway.controller.project;

import com.nhnacademy.gateway.common.adaptor.login.MemberNameAdaptor;
import com.nhnacademy.gateway.model.domain.Project;
import com.nhnacademy.gateway.model.request.member.MemberIdRequest;
import com.nhnacademy.gateway.model.dto.ResponseUserNameDto;
import com.nhnacademy.gateway.service.ProjectService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/member")
@Slf4j
public class ProjectController {

    @Autowired
    private MemberNameAdaptor userNameAdaptor;
    @Autowired
    private ProjectService projectService;

    @GetMapping
    public String getProjects(Model model,
                              @RequestParam("memberId") String memberId) {
        ResponseUserNameDto response = userNameAdaptor.sendResponseUserNameDto(new MemberIdRequest(memberId));
        model.addAttribute("memberName", response.getUserName());
        model.addAttribute("memberId", memberId);

        List<Project> projects = projectService.getProjectsByMemberId(new MemberIdRequest(memberId));
        for(Project project : projects) {
            log.error("{}", project.getProjectName());
        }
        model.addAttribute("projects", projects);

        return "ProjectMainForm";
    }

}
