package com.nhnacademy.gateway.common.adaptor.project;

import com.nhnacademy.gateway.exception.ResponseDtoException;
import com.nhnacademy.gateway.model.dto.ResponseProjectMembersDto;
import com.nhnacademy.gateway.model.request.project.ProjectIdRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
public class ProjectGetProjectMembersAdaptor {
    private static final String PROJECT_GET_MEMBERS_API_URL = "http://localhost:7070/project/";

    private final RestTemplate restTemplate;

    public ProjectGetProjectMembersAdaptor(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public ResponseProjectMembersDto sendAndGetProjectMembers(ProjectIdRequest projectIdRequest, long projectId) {

        try {
            String url = PROJECT_GET_MEMBERS_API_URL + String.valueOf(projectId) + "/members";
            ResponseEntity<ResponseProjectMembersDto> response = restTemplate.getForEntity(url, ResponseProjectMembersDto.class);

            if(!response.getStatusCode().is2xxSuccessful()) {
                throw new ResponseDtoException("Project의 회원 목록을 가져오지 못했습니다.");
            }

            return response.getBody();
        }
        catch(Exception e) {
            throw new ResponseDtoException("Project의 회원 목록을 가져오지 못했습니다.");
        }

    }

}
