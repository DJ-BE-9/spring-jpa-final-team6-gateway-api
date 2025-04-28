package com.nhnacademy.gateway.common.adaptor.task;

import com.nhnacademy.gateway.exception.ResponseDtoException;
import com.nhnacademy.gateway.model.dto.task.ResponseTaskDto;
import com.nhnacademy.gateway.model.request.task.RegisterTaskRequest;
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
public class TaskPutUpdateAdaptor {
    // TODO UPDATE 로직 수정
    private static final String PROJECT_API = "http://localhost:7070/project/";

    private final RestTemplate restTemplate;

    public long sendRegisterRequest(long projectId, RegisterTaskRequest registerTaskRequest) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        String api = PROJECT_API + projectId + "/task";

        HttpEntity<RegisterTaskRequest> requestHttpEntity = new HttpEntity<>(registerTaskRequest, headers);

        try {
            ResponseEntity<ResponseTaskDto> response = restTemplate.postForEntity(api, requestHttpEntity, ResponseTaskDto.class);
            ResponseTaskDto responseTaskDto = response.getBody();
            if(!response.getStatusCode().is2xxSuccessful()) {
                throw new ResponseDtoException("태스크를 등록하지 못했습니다.");
            }
            return responseTaskDto.getTaskId();
        }
        catch(Exception e) {
            throw new ResponseDtoException("서버에서 해당 태스크 등록 요청 응답을 받지 못했습니다");
        }

    }
}
