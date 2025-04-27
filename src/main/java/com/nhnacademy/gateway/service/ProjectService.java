package com.nhnacademy.gateway.service;

import com.nhnacademy.gateway.common.adaptor.project.ProjectGetProjectDetailAdaptor;
import com.nhnacademy.gateway.common.adaptor.project.ProjectGetProjectsAdaptor;
import com.nhnacademy.gateway.common.adaptor.project.ProjectPostMemberRegisterAdaptor;
import com.nhnacademy.gateway.common.adaptor.project.ProjectPostRegisterAdaptor;
import com.nhnacademy.gateway.exception.EmptyRequestException;
import com.nhnacademy.gateway.exception.MemberRegisterProcessException;
import com.nhnacademy.gateway.model.domain.Project;
import com.nhnacademy.gateway.model.dto.ResponseProjectDto;
import com.nhnacademy.gateway.model.dto.ResponseProjectsDto;
import com.nhnacademy.gateway.model.request.member.MemberIdRequest;
import com.nhnacademy.gateway.model.request.project.RegisterProjectMemberRequest;
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
    private ProjectPostRegisterAdaptor projectPostProjectAdaptor;
    @Autowired
    private ProjectPostMemberRegisterAdaptor projectPostMemberRegisterAdaptor;
    @Autowired
    private ProjectPostRegisterAdaptor projectPostRegisterAdaptor;
    @Autowired
    private ProjectGetProjectDetailAdaptor projectGetProjectDetailAdaptor;


    public List<Project> getProjectsByMemberId(MemberIdRequest memberIdRequest) {
        if(Objects.isNull(memberIdRequest) || Objects.isNull(memberIdRequest.getMemberId()) || memberIdRequest.getMemberId().isEmpty()) {
            throw new EmptyRequestException("MemberIdRequest 값을 받지 못했습니다");
        }
        ResponseProjectsDto responseProjectsDto = projectGetProjectsAdaptor.sendAndGetProjects(memberIdRequest);


        return responseProjectsDto.getProjects();
    }

    public void postProject(RegisterProjectRequest projectRequest, String memberId) {
        if(Objects.isNull(projectRequest)) {
            throw new EmptyRequestException("ProjectRequest 값을 받지 못했습니다");
        }

        long projectId = projectPostProjectAdaptor.sendRegisterRequest(projectRequest, memberId);

        if(!projectPostMemberRegisterAdaptor.sendRegisterProjectMember(projectId, new RegisterProjectMemberRequest(true, memberId))) {
            throw new MemberRegisterProcessException("Project Member 등록하지 못했습니다.");
        }

    }

    public Project getProjectByProjectId(Long projectId) {
        if(Objects.isNull(projectId)) {
            throw new EmptyRequestException("ProjectId 값을 받지 못했습니다.");
        }
        ResponseProjectDto responseProjectDto = projectGetProjectDetailAdaptor.sendAndGetProject(projectId);
        return new Project(
                responseProjectDto.getProjectId(),
                responseProjectDto.getProjectName(),
                responseProjectDto.getProjectState()
        );
    }

}
