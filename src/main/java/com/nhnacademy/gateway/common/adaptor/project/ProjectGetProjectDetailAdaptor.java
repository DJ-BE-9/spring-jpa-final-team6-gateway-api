package com.nhnacademy.gateway.common.adaptor.project;

import com.nhnacademy.gateway.exception.ResponseDtoException;
import com.nhnacademy.gateway.model.dto.ResponseProjectDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Component
public class ProjectGetProjectDetailAdaptor { // 프로젝트 상세 페이지
    private static final String PROJECT_API_URL = "http://localhost:7070/project/";

    private final RestTemplate restTemplate;

    public ProjectGetProjectDetailAdaptor(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public ResponseProjectDto sendAndGetProject(Long projectId) {

        try {
            String url = PROJECT_API_URL + projectId;
            log.info(url);
            ResponseEntity<ResponseProjectDto> response = restTemplate.getForEntity(url, ResponseProjectDto.class);

            if (!response.getStatusCode().is2xxSuccessful() || response.getBody() == null) {
                throw new ResponseDtoException("Project 상세 정보를 가져오지 못했습니다.");
            }

            return response.getBody();
        } catch(Exception e) {
            log.error("try-catch: {}", e, e);
            throw new ResponseDtoException("Project 상세 정보를 가져오지 못했습니다.");
        }
    }
}
