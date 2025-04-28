package com.nhnacademy.gateway.controller.task;

import com.nhnacademy.gateway.model.dto.milestone.ResponseMilestoneDto;
import com.nhnacademy.gateway.model.dto.milestone.ResponseMilestonesDto;
import com.nhnacademy.gateway.model.dto.tag.ResponseGetTagDto;
import com.nhnacademy.gateway.model.dto.tag.TagRequest;
import com.nhnacademy.gateway.model.dto.task.ResponseTaskDto;
import com.nhnacademy.gateway.service.MilestoneService;
import com.nhnacademy.gateway.service.TaskService;
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

    private MilestoneService milestoneService;

    public TaskController(MilestoneService milestoneService) {
        this.milestoneService = milestoneService;
    }


    @GetMapping("/task")
    public String registerTaskForm(Model model, @PathVariable("projectId") long projectId) {
        ResponseMilestonesDto milestonesDto = milestoneService.getMilestones(projectId);

        List<ResponseMilestoneDto> milestoneList = milestonesDto.getMilestones();
        model.addAttribute("projectId", projectId);
        model.addAttribute("milestones", milestoneList);

        List tags = new ArrayList();
        model.addAttribute("tags", tags); // TODO 해당 프로젝트에 등록된 태그 목록 가져오기
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

        return "projectTaskDetailForm";
    }



}
