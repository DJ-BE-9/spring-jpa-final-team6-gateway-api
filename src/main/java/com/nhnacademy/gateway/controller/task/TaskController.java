package com.nhnacademy.gateway.controller.task;

import com.nhnacademy.gateway.model.dto.comment.ResponseCommentListDto;
import com.nhnacademy.gateway.model.dto.milestone.ResponseMilestoneDto;
import com.nhnacademy.gateway.model.dto.milestone.ResponseMilestonesDto;
import com.nhnacademy.gateway.model.dto.tag.*;
import com.nhnacademy.gateway.model.dto.task.ResponseTaskDto;
import com.nhnacademy.gateway.model.request.comment.RegisterCommentRequest;
import com.nhnacademy.gateway.service.CommentService;
import com.nhnacademy.gateway.service.MilestoneService;
import com.nhnacademy.gateway.service.TagService;
import com.nhnacademy.gateway.service.TaskService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/project/{projectId}")
@Slf4j
public class TaskController {

    private MilestoneService milestoneService;
    private TagService tagService;
    @Autowired
    private TaskService taskService;
    @Autowired
    private CommentService commentService;

    @Autowired
    private StringRedisTemplate stringStringRedisTemplate;

    public TaskController(MilestoneService milestoneService, TagService tagService) {
        this.milestoneService = milestoneService;
        this.tagService = tagService;
    }


    @GetMapping("/task")
    public String registerTaskForm(Model model, @PathVariable("projectId") long projectId) {
        ResponseMilestonesDto milestonesDto = milestoneService.getMilestones(projectId);

        List<ResponseMilestoneDto> milestoneList = milestonesDto.getMilestones();
        model.addAttribute("projectId", projectId);
        model.addAttribute("milestones", milestoneList);

        List<ResponseGetTagDto> tags = tagService.getTagsByProjectId(new TagRequest(projectId));
        model.addAttribute("tags", tags);

        return "projectTaskRegisterForm";
    }

    @GetMapping("/task/{taskId}")
    public String getTask(Model model,
                          HttpServletRequest request,
                          @PathVariable("projectId") long projectId,
                          @PathVariable("taskId") long taskId) {

        String sessionId = getSessionIdFromCookie(request);

        String memberId = stringStringRedisTemplate.opsForValue().get(sessionId);
        if (memberId != null) {
            model.addAttribute("memberId", memberId);
        }

        ResponseTaskDto task = taskService.getTask(projectId, taskId);
        model.addAttribute("task", task);

        ResponseTagListByTaskDto responseTagListByTaskDto = tagService.getTags(projectId, taskId);
        model.addAttribute("tags", responseTagListByTaskDto.getTagList());

        ResponseCommentListDto comments = commentService.getComments(projectId, taskId);
        model.addAttribute("comments", comments.getComments());

        return "projectTaskDetailForm";

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
