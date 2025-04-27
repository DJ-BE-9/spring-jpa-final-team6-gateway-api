package com.nhnacademy.gateway.common.adaptor.project;

import com.nhnacademy.gateway.exception.ResponseDtoException;
import com.nhnacademy.gateway.model.domain.Project;
import com.nhnacademy.gateway.model.request.project.RegisterProjectMemberRequest;
import com.nhnacademy.gateway.model.request.project.RegisterProjectRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;

@Slf4j
@Component
public class ProjectPostProjectAdaptor {
    private static final String PROJECT_API_URL = "http://localhost:7070/project";

    @Autowired
    private RestTemplate restTemplate;

    public ProjectPostProjectAdaptor() {
        this.restTemplate = new RestTemplate();
    }

    public Project sendProjectRegisterRequest(RegisterProjectRequest projectRequest) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        log.info("projectRequest:{} {}", projectRequest.getProjectName(), projectRequest.getProjectState());

        HttpEntity<RegisterProjectRequest> requestHttpEntity = new HttpEntity<>(projectRequest, headers);

        log.info("projectRequest:{} {}", projectRequest.getProjectName(), projectRequest.getProjectState());
        log.info("projectRequest:{} {}", requestHttpEntity.getBody().getProjectName(), requestHttpEntity.getBody().getProjectState());
        try {
            ResponseEntity<Project> response = restTemplate.postForEntity(PROJECT_API_URL, requestHttpEntity, Project.class);
            log.info("{}", response.getBody());
            if(!response.getStatusCode().is2xxSuccessful() || Objects.isNull(response)) {
                throw new ResponseDtoException("Project 생성하지 못했습니다.");
            }
            return response.getBody();
        }
        catch(Exception e) {
            throw new ResponseDtoException("Project 생성하지 못했습니다.");
        }
    }

    public void sendProjectMemberRegisterRequest(Project project, RegisterProjectMemberRequest request) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        String projectMemberRegisterApi = PROJECT_API_URL + "/"+project.getProjectId() +"/members";

        log.info("{}, {}", request.getUserId(), request.isProjectManager());

        HttpEntity<RegisterProjectMemberRequest> requestHttpEntity = new HttpEntity<>(request, headers);

        try {
            ResponseEntity<Project> response = restTemplate.postForEntity(projectMemberRegisterApi, requestHttpEntity, Project.class);
            if(!response.getStatusCode().is2xxSuccessful() || Objects.isNull(response)) {
                throw new ResponseDtoException("해당 Project에 Member를 등록하지 못했습니다.");
            }
        } catch(Exception e) {
            throw new ResponseDtoException("요청을 보내지 못하였습니다.");
        }

    }

}
