package com.nhnacademy.gateway.service;

import com.nhnacademy.gateway.common.adaptor.project.ProjectGetProjectDetailAdapter;
import com.nhnacademy.gateway.common.adaptor.project.ProjectGetProjectsAdaptor;
import com.nhnacademy.gateway.common.adaptor.project.ProjectPostProjectAdaptor;
import com.nhnacademy.gateway.exception.EmptyRequestException;
import com.nhnacademy.gateway.model.domain.Project;
import com.nhnacademy.gateway.model.dto.ResponseProjectDto;
import com.nhnacademy.gateway.model.dto.ResponseProjectsDto;
import com.nhnacademy.gateway.model.request.member.MemberIdRequest;
import com.nhnacademy.gateway.model.request.project.RegisterProjectRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Slf4j
@Service
@Transactional
public class ProjectService {

    @Autowired
    private ProjectGetProjectsAdaptor projectGetProjectsAdaptor;
    @Autowired
    private ProjectPostProjectAdaptor projectPostProjectAdaptor;
    @Autowired
    private ProjectGetProjectDetailAdapter projectGetProjectDetailAdapter;

    public List<Project> getProjectsByMemberId(MemberIdRequest memberIdRequest) {
        if(Objects.isNull(memberIdRequest) || Objects.isNull(memberIdRequest.getMemberId()) || memberIdRequest.getMemberId().isEmpty()) {
            throw new EmptyRequestException("MemberIdRequest 값을 받지 못했습니다");
        }
        ResponseProjectsDto responseProjectsDto = projectGetProjectsAdaptor.sendAndGetProjects(memberIdRequest);


        return responseProjectsDto.getProjects();
    }

    public void postProject(RegisterProjectRequest projectRequest) {
        if(Objects.isNull(projectRequest)) {
            throw new EmptyRequestException("ProjectRequest 값을 받지 못했습니다");
        }

        if(projectPostProjectAdaptor.sendProjectRegisterRequest(projectRequest)) {

        }

    }

    public Project getProjectByProjectId(Long projectId) {
        if(Objects.isNull(projectId)) {
            throw new EmptyRequestException("ProjectId 값을 받지 못했습니다.");
        }
        ResponseProjectDto responseProjectDto = projectGetProjectDetailAdapter.sendAndGetProject(projectId);
        return new Project(
                responseProjectDto.getProjectId(),
                responseProjectDto.getProjectName(),
                responseProjectDto.getProjectState()
        );
    }

}
