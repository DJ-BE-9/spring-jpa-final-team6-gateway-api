package com.nhnacademy.gateway.controller.task;

import com.nhnacademy.gateway.model.dto.milestone.ResponseMilestoneDto;
import com.nhnacademy.gateway.model.dto.milestone.ResponseMilestonesDto;
import com.nhnacademy.gateway.service.MilestoneService;
import lombok.extern.slf4j.Slf4j;
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




}
