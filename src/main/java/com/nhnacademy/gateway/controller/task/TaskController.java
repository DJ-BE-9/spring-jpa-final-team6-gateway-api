package com.nhnacademy.gateway.controller.task;

import com.nhnacademy.gateway.model.dto.milestone.ResponseMilestoneDto;
import com.nhnacademy.gateway.model.dto.milestone.ResponseMilestonesDto;
import com.nhnacademy.gateway.model.dto.tag.ResponseGetTagDto;
import com.nhnacademy.gateway.model.dto.tag.TagRequest;
import com.nhnacademy.gateway.model.dto.task.ResponseTaskDto;
import com.nhnacademy.gateway.model.dto.tag.ResponseGetTagDto;
import com.nhnacademy.gateway.model.dto.tag.ResponseGetTagsDto;
import com.nhnacademy.gateway.model.dto.tag.ResponseTagDto;
import com.nhnacademy.gateway.model.dto.tag.TagRequest;
import com.nhnacademy.gateway.model.request.comment.RegisterCommentRequest;
import com.nhnacademy.gateway.service.CommentService;
import com.nhnacademy.gateway.service.MilestoneService;
import com.nhnacademy.gateway.service.TaskService;
import com.nhnacademy.gateway.service.TagService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/project/{projectId}")
@Slf4j
public class TaskController {

    @Autowired
    private TaskService taskService;
    @Autowired
    private CommentService commentService;

    private MilestoneService milestoneService;
    private TagService tagService;

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
                          @PathVariable("projectId") long projectId,
                          @PathVariable("taskId") long taskId) {

        ResponseTaskDto responseTaskDto = taskService.getTaskDetail(projectId, taskId);
        model.addAttribute("task", responseTaskDto);

        List<ResponseGetTagDto> tags = taskService.getTagIds(new TagRequest(projectId));
        model.addAttribute("tags", tags);

        //TODO# comments addAttribute
        List<RegisterCommentRequest> comments = commentService.getComments(projectId, taskId);
        model.addAttribute("comments", comments);

        return "projectTaskDetailForm";
    }



}
