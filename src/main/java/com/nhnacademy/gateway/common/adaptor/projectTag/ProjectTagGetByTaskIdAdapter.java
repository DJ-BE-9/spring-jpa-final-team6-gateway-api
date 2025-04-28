package com.nhnacademy.gateway.common.adaptor.projectTag;

import com.nhnacademy.gateway.exception.ResponseDtoException;
import com.nhnacademy.gateway.model.dto.tag.ResponseTagListByTaskDto;
import com.nhnacademy.gateway.model.request.projectTag.RegisterProjectTagRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@RequiredArgsConstructor
public class ProjectTagGetByTaskIdAdapter {
    private static final String PROJECT_API = "http://localhost:7070/project/";

    private final RestTemplate restTemplate;

    public ResponseTagListByTaskDto sendGetTagListByTaskId(long projectId, long taskId) {

        String api = PROJECT_API + projectId + "/task/" + taskId + "/projectTag";

        try {
            ResponseEntity<ResponseTagListByTaskDto> response = restTemplate.getForEntity(api, ResponseTagListByTaskDto.class);
            if(!response.getStatusCode().is2xxSuccessful()) {
                throw new ResponseDtoException("태스크에 해당 태그를 등록하지 못했습니다.");
            }

            return response.getBody();
        }
        catch(Exception e) {
            throw new ResponseDtoException("서버에서 해당 태스크 태그 등록 요청 응답을 받지 못했습니다");
        }

    }
}
