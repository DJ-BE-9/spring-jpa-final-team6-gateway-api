package com.nhnacademy.gateway.common.adaptor.project;

import com.nhnacademy.gateway.model.dto.ResponseDto;
import com.nhnacademy.gateway.model.request.project.RegisterProjectRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Component
public class ProjectPostRegisterAdaptor {

    private static final String REGISTER_API_URL = "http://localhost:7070/project";

    private RestTemplate restTemplate;

    public ProjectPostRegisterAdaptor() {
        restTemplate = new RestTemplate();
    }

    public boolean sendRegisterRequest(RegisterProjectRequest projectRequest, String memberId) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<RegisterProjectRequest> requestHttpEntity = new HttpEntity<>(projectRequest, headers);

        try {
            ResponseEntity<ResponseDto> response = restTemplate.postForEntity(REGISTER_API_URL, requestHttpEntity, ResponseDto.class);

            return response.getStatusCode().is2xxSuccessful();
        }
        catch(Exception e) {
            return false;
        }

    }

}
