package com.nhnacademy.gateway.common.adaptor.project;

import com.nhnacademy.gateway.exception.ResponseDtoException;
import com.nhnacademy.gateway.model.domain.Project;
import com.nhnacademy.gateway.model.dto.ResponseProjectsDto;
import com.nhnacademy.gateway.model.request.member.MemberIdRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * memberId 값에 대한 Project 리스트를 가져오는 Adaptor
 */
@Slf4j
@Component
public class ProjectGetProjectsAdaptor {
    private static final String PROJECT_API_URL = "http://localhost:7070/member/";

    private final RestTemplate restTemplate;

    public ProjectGetProjectsAdaptor(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public ResponseProjectsDto sendAndGetProjects(MemberIdRequest memberIdRequest) {

        try {
            ResponseEntity<ResponseProjectsDto> response = restTemplate.getForEntity(PROJECT_API_URL + memberIdRequest.getMemberId(), ResponseProjectsDto.class);
            List<Project> projects = response.getBody().getProjects();
            projects.forEach(project -> {log.info("{}", project.getProjectName());});
            if (!response.getStatusCode().is2xxSuccessful()) {
                throw new ResponseDtoException("Project 리스트를 가져오지 못했습니다.");
            }

            return response.getBody();
        } catch(Exception e) {
            log.error("try-catch: {}", e, e);
            throw new ResponseDtoException("Project 리스트를 가져오지 못했습니다.");
        }
    }

}
