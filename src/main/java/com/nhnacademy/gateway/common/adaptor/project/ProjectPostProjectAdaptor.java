package com.nhnacademy.gateway.common.adaptor.project;

import com.nhnacademy.gateway.exception.ResponseDtoException;
import com.nhnacademy.gateway.model.request.project.RegisterProjectRequest;
import lombok.extern.slf4j.Slf4j;
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

    private RestTemplate restTemplate;

    public ProjectPostProjectAdaptor() {
        this.restTemplate = new RestTemplate();
    }

    public boolean sendProjectRegisterRequest(RegisterProjectRequest projectRequest) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        log.info("projectRequest:{} {}", projectRequest.getProjectName(), projectRequest.getProjectState());

        HttpEntity<RegisterProjectRequest> requestHttpEntity = new HttpEntity<>(projectRequest, headers);

        log.info("projectRequest:{} {}", projectRequest.getProjectName(), projectRequest.getProjectState());
        log.info("projectRequest:{} {}", requestHttpEntity.getBody().getProjectName(), requestHttpEntity.getBody().getProjectState());
        try {
            ResponseEntity<String> response = restTemplate.postForEntity(PROJECT_API_URL, requestHttpEntity, String.class);

            if(!response.getStatusCode().is2xxSuccessful() || Objects.isNull(response)) {
                throw new ResponseDtoException("Project 생성하지 못했습니다.");
            }

            return true;
        }
        catch(Exception e) {
            throw new ResponseDtoException("Project 생성하지 못했습니다.");
        }

    }

}
