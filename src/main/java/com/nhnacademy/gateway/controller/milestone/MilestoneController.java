package com.nhnacademy.gateway.controller.milestone;

import com.nhnacademy.gateway.model.request.milestone.UpdateMilestoneRequest;
import com.nhnacademy.gateway.service.MilestoneService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/project/{projectId}/milestone/{milestoneId}")
public class MilestoneController {

    private final MilestoneService milestoneService;

    public MilestoneController(MilestoneService milestoneService) {
        this.milestoneService = milestoneService;
    }

    @PutMapping
    public ResponseEntity<Void> updateMilestoneByProject(
            @PathVariable long projectId,
            @PathVariable long milestoneId,
            @RequestBody UpdateMilestoneRequest updateMilestoneRequest) {

        milestoneService.updateMilestone(projectId, milestoneId, updateMilestoneRequest);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteMilestoneByProject(
            @PathVariable long projectId,
            @PathVariable long milestoneId) {
        milestoneService.deleteMilestone(projectId, milestoneId);
        return ResponseEntity.ok().build();
    }


}
