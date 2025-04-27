package com.nhnacademy.gateway.common.adaptor.project;

import com.nhnacademy.gateway.model.dto.ResponseDto;
import com.nhnacademy.gateway.model.request.project.RegisterProjectMemberRequest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class ProjectPostMemberRegisterAdaptor {
    private static final String REGISTER_MEMBER_URL = "http://localhost:7070/project/";

    private RestTemplate restTemplate;

    public ProjectPostMemberRegisterAdaptor() {
        restTemplate = new RestTemplate();
    }

    public boolean sendRegisterProjectMember(long projectId, RegisterProjectMemberRequest registerProjectMemberRequest) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<RegisterProjectMemberRequest> requestHttpEntity = new HttpEntity<>(registerProjectMemberRequest, headers);

        try {
            String url = REGISTER_MEMBER_URL + projectId + "/members";
            ResponseEntity<ResponseDto> response = restTemplate.postForEntity(url, requestHttpEntity, ResponseDto.class);

            return response.getStatusCode().is2xxSuccessful();
        }
        catch (Exception e) {
            return false;
        }

    }


}
