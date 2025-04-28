package com.nhnacademy.gateway.common.adaptor.tag;

import com.nhnacademy.gateway.model.dto.ResponseDto;
import com.nhnacademy.gateway.model.dto.tag.TagRegisterToProjectRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpHeaders;

@Slf4j
@Component
public class TagRegisterAdapter {
    private static final String PROJECT_API_URL = "http://localhost:7070/project/";

    private final RestTemplate restTemplate;

    public TagRegisterAdapter(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public void sendRegisterRequest(long projectId, TagRegisterToProjectRequest registerRequest) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<TagRegisterToProjectRequest> requestHttpEntity = new HttpEntity<>(registerRequest, headers);

        try{
            String url = PROJECT_API_URL + projectId + "/tag";
            log.info(url);
            ResponseEntity<Void> response = restTemplate.postForEntity(url, requestHttpEntity, Void.class);

            response.getStatusCode().is2xxSuccessful();
        }catch (Exception e){
            log.error("Tag 등록 요청 실패",e);
        }
    }
}
