package com.nhnacademy.gateway.common.adaptor.projectTag;

import com.nhnacademy.gateway.exception.ResponseDtoException;
import com.nhnacademy.gateway.model.request.projectTag.RegisterProjectTagRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@RequiredArgsConstructor
@Slf4j
public class ProjectTagPostRegisterAdaptor {
    private static final String PROJECT_API = "http://localhost:7070/project/";

    private final RestTemplate restTemplate;

    public boolean sendRegisterRequest(long projectId, long taskId, RegisterProjectTagRequest registerProjectTagRequest) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        String api = PROJECT_API + projectId + "/task/" + taskId + "/projectTag";

        HttpEntity<RegisterProjectTagRequest> requestHttpEntity = new HttpEntity<>(registerProjectTagRequest, headers);

        try {
            ResponseEntity<Void> response = restTemplate.postForEntity(api, requestHttpEntity, Void.class);
            if(!response.getStatusCode().is2xxSuccessful()) {
                throw new ResponseDtoException("태스크에 해당 태그를 등록하지 못했습니다.");
            }

            return true;
        }
        catch(Exception e) {
            throw new ResponseDtoException("서버에서 해당 태스크 태그 등록 요청 응답을 받지 못했습니다");
        }

    }
}
