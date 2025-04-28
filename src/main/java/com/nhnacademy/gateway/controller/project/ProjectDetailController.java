package com.nhnacademy.gateway.controller.project;

import com.nhnacademy.gateway.common.adaptor.login.MemberStatusAdaptor;
import com.nhnacademy.gateway.common.adaptor.project.ProjectPostProjectStateAdaptor;
import com.nhnacademy.gateway.model.domain.Project;
import com.nhnacademy.gateway.model.dto.ResponseDto;
import com.nhnacademy.gateway.model.dto.ResponseProjectDto;
import com.nhnacademy.gateway.model.dto.task.ResponseTaskDetailDto;
import com.nhnacademy.gateway.model.request.member.MemberCudRequest;
import com.nhnacademy.gateway.model.request.project.ProjectIdRequest;
import com.nhnacademy.gateway.model.request.project.ProjectMember;
import com.nhnacademy.gateway.model.request.project.ProjectStateRequest;
import com.nhnacademy.gateway.model.type.Cud;
import com.nhnacademy.gateway.model.type.State;
import com.nhnacademy.gateway.service.ProjectService;
import com.nhnacademy.gateway.service.TaskService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/project/{projectId}")
public class ProjectDetailController {

    @Autowired
    private ProjectService projectService;
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;
    @Autowired
    private MemberStatusAdaptor memberStatusAdaptor;
    @Autowired
    private ProjectPostProjectStateAdaptor projectPostProjectStateAdaptor;
    // TODO Task 목록 보여주기
    @Autowired
    private TaskService taskService;

    @GetMapping
    public String getProjectDetail(@PathVariable("projectId") long projectId, Model model,
                                   HttpServletRequest request) {
        Project project = projectService.getProjectByProjectId(projectId);
        model.addAttribute("projectTitle", project.getProjectName());
        model.addAttribute("projectState", project.getProjectState());

        List<ProjectMember> members = projectService.getProjectMembers(new ProjectIdRequest(projectId), projectId);
        model.addAttribute("projectMembers", members);

        String sessionId = getSessionIdFromCookie(request);

        Object value = redisTemplate.opsForValue().get(sessionId);
        if (value != null) {
            String memberId = value.toString();
            model.addAttribute("memberId", memberId);
        }

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

    @PostMapping("/members/{memberId}")
    public String postProjectMemberDelete(@PathVariable("projectId") long projectId,
                                          @PathVariable("memberId") String memberId) {

        projectService.deleteProjectMember(projectId, memberId);

        return "redirect:/project/" + projectId;
    }

    @PostMapping("/members/{memberId}/state")
    public String postProjectMemberState(@PathVariable("projectId") long projectId,
                                         @PathVariable("memberId") String memberId,
                                         @RequestParam("projectState") State state) {

        String response = projectPostProjectStateAdaptor.sendProjectStatusRequest(new ProjectStateRequest(projectId, state), memberId);
        if(state == State.COMPLETED || state == State.DORMANT) {
            return "redirect:/member?memberId=" + memberId;
        }

        return "redirect:/project/" + String.valueOf(projectId) +"/members/" + memberId + "/state";
    }

    private String getSessionIdFromCookie(HttpServletRequest request) {
        if(request.getCookies() != null) {
            for(Cookie cookie : request.getCookies()) {
                if(cookie.getName().equals("SESSIONID")) {
                    return cookie.getValue();
                }
            }
        }

        return null;
    }

}