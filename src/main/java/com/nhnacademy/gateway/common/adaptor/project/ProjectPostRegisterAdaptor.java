package com.nhnacademy.gateway.common.adaptor.project;

import com.nhnacademy.gateway.exception.LoginProcessException;
import com.nhnacademy.gateway.exception.ResponseDtoException;
import com.nhnacademy.gateway.model.dto.ResponseDto;
import com.nhnacademy.gateway.model.dto.ResponseProjectIdDto;
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
public class ProjectPostRegisterAdaptor {

    private static final String REGISTER_API_URL = "http://localhost:7070/project";

    private RestTemplate restTemplate;

    public ProjectPostRegisterAdaptor() {
        restTemplate = new RestTemplate();
    }

    public long sendRegisterRequest(RegisterProjectRequest projectRequest, String memberId) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<RegisterProjectRequest> requestHttpEntity = new HttpEntity<>(projectRequest, headers);

        try {
            ResponseEntity<ResponseProjectIdDto> response = restTemplate.postForEntity(REGISTER_API_URL, requestHttpEntity, ResponseProjectIdDto.class);

            if(!response.getStatusCode().is2xxSuccessful() || Objects.isNull(response.getBody())) {
                throw new ResponseDtoException("프로젝트를 등록하지 못했습니다.");
            }

            return response.getBody().getProjectId();
        }
        catch(Exception e) {
            throw new ResponseDtoException("프로젝트를 등록하지 못했습니다.");
        }

    }

}
