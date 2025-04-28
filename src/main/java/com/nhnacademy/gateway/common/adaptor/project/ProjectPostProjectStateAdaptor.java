package com.nhnacademy.gateway.common.adaptor.project;

import com.nhnacademy.gateway.exception.ResponseDtoException;
import com.nhnacademy.gateway.model.dto.ResponseDto;
import com.nhnacademy.gateway.model.dto.ResponseProjectDto;
import com.nhnacademy.gateway.model.request.project.ProjectStateRequest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;

@Component
public class ProjectPostProjectStateAdaptor {
    private static final String PROJECT_STATE_API_URL = "http://localhost:7070/project/";

    private RestTemplate restTemplate;

    public ProjectPostProjectStateAdaptor(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String sendProjectStatusRequest(ProjectStateRequest projectStateRequest, String memberId) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<ProjectStateRequest> requestHttpEntity = new HttpEntity<>(projectStateRequest, headers);

        try {
            String url = PROJECT_STATE_API_URL + projectStateRequest.getProjectId() + "/members/" + memberId + "/state";
            ResponseEntity<String> response = restTemplate.postForEntity(url, requestHttpEntity, String.class);

            if(!response.getStatusCode().is2xxSuccessful() || Objects.isNull(response)) {
                throw new ResponseDtoException("Project 상태를 바꾸지 못했습니다.");
            }

            return response.getBody();
        }
        catch(Exception e) {
            throw new ResponseDtoException("Project 상태를 바꾸지 못했습니다.");
        }

    }


}
