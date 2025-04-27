package com.nhnacademy.gateway.common.adaptor.tag;

import com.nhnacademy.gateway.model.dto.ResponseDto;
import com.nhnacademy.gateway.model.dto.tag.TagRegisterToProjectRequest;
import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpHeaders;

@Component
public class ProjectRegisterTagAdapter {
    private static final String PROJECT_API_URL = "http://localhost:7070/project/";

    private final RestTemplate restTemplate;

    public ProjectRegisterTagAdapter(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public boolean sendRegisterRequest(TagRegisterToProjectRequest registerRequest) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<TagRegisterToProjectRequest> requestHttpEntity = new HttpEntity<>(registerRequest, headers);

        try{
            ResponseEntity<ResponseDto> response = restTemplate.postForEntity(PROJECT_API_URL, requestHttpEntity, ResponseDto.class);

            return response.getStatusCode().is2xxSuccessful();
        }catch (Exception e){
            return false;
        }
    }
}
