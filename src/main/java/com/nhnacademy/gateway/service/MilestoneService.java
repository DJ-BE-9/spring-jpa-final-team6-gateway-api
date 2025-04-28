package com.nhnacademy.gateway.service;

import com.nhnacademy.gateway.common.adaptor.milestone.*;
import com.nhnacademy.gateway.exception.EmptyRequestException;
import com.nhnacademy.gateway.model.dto.milestone.ResponseMilestoneDto;
import com.nhnacademy.gateway.model.dto.milestone.ResponseMilestonesDto;
import com.nhnacademy.gateway.model.request.milestone.RegisterMilestoneRequest;
import com.nhnacademy.gateway.model.request.milestone.UpdateMilestoneRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Service
@Transactional
@Slf4j
public class MilestoneService {
    @Autowired
    private MilestonePostRegisterAdaptor milestonePostRegisterAdaptor;

    @Autowired
    private MilestoneGetMilestonesAdaptor milestoneGetMilestonesAdaptor;

    @Autowired
    private MilestonePutUpdateAdaptor milestonePutUpdateAdaptor;
    @Autowired
    private MilestoneDeleteAdaptor milestoneDeleteAdaptor;

    @Autowired
    private MilestoneGetByMilestoneIdAdaptor milestoneGetByMilestoneIdAdaptor;

    public ResponseMilestonesDto getMilestones(long projectId) {
        log.info("{}", projectId);
        return milestoneGetMilestonesAdaptor.sendGetMilestonesRequest(projectId);
    }

    public void registerMilestone(long projectId, RegisterMilestoneRequest registerMilestoneRequest) {
        if(Objects.isNull(registerMilestoneRequest)) {
            throw new EmptyRequestException("RegisterMilestoneRequest 값을 받지 못했습니다");
        }
        log.info("{}", projectId);
        log.info("{}", registerMilestoneRequest.getMilestoneName());
        log.info("{}", registerMilestoneRequest.getMilestoneStartedAt());
        log.info("{}", registerMilestoneRequest.getMilestoneEndedAt());
        milestonePostRegisterAdaptor.sendRegisterRequest(projectId, registerMilestoneRequest);
    }

    public void updateMilestone(long projectId, long milestoneId, UpdateMilestoneRequest updateMilestoneRequest) {
        if(Objects.isNull(updateMilestoneRequest)) {
            throw new EmptyRequestException("UpdateMilestoneRequest 값을 받지 못했습니다");
        }
        log.info("{}", updateMilestoneRequest.getMilestoneName());
        log.info("{}", updateMilestoneRequest.getMilestoneStartedAt());
        log.info("{}", updateMilestoneRequest.getMilestoneEndedAt());

        milestonePutUpdateAdaptor.sendUpdateRequest(projectId, milestoneId, updateMilestoneRequest);
    }

    public void deleteMilestone(long projectId, long milestoneId) {
        log.info("{}", projectId);
        log.info("{}", milestoneId);
        milestoneDeleteAdaptor.sendDeleteRequest(projectId, milestoneId);
    }

    public ResponseMilestoneDto getMilestone(long milestoneId) {
        return milestoneGetByMilestoneIdAdaptor.sendGetMilestonesRequest(milestoneId);
    }

}
