package com.nhnacademy.gateway.controller.milestone;

import com.nhnacademy.gateway.model.dto.milestone.ResponseMilestonesDto;
import com.nhnacademy.gateway.model.request.milestone.RegisterMilestoneRequest;
import com.nhnacademy.gateway.service.MilestoneService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/project/{projectId}/milestone")
public class MilestoneRegisterController {

    private final MilestoneService milestoneService;

    public MilestoneRegisterController(MilestoneService milestoneService) {
        this.milestoneService = milestoneService;
    }

    // 해당 프로젝트의 마일스톤 등록
    @PostMapping
    public ResponseEntity<Void> registerMilestone(@PathVariable long projectId,
                                              @RequestBody RegisterMilestoneRequest request) {
        milestoneService.registerMilestone(projectId, request);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseMilestonesDto getMilestones(@PathVariable long projectId) {
        return milestoneService.getMilestones(projectId);
    }

}
