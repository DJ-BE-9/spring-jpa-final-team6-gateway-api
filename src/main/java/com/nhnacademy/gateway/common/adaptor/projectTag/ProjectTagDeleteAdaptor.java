package com.nhnacademy.gateway.common.adaptor.projectTag;

import com.nhnacademy.gateway.exception.ResponseDtoException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@RequiredArgsConstructor
@Slf4j
public class ProjectTagDeleteAdaptor {

    private static final String PROJECT_API = "http://localhost:7070/project/";

    private final RestTemplate restTemplate;

    public void sendDeleteProjectTag(long projectId, long taskId) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        String api = PROJECT_API + projectId + "/task/" + taskId+ "/projectTag";

        HttpEntity<Void> requestHttpEntity = new HttpEntity<>(headers);

        try {
            ResponseEntity<Void> response = restTemplate.exchange(
                    api,
                    HttpMethod.DELETE,
                    requestHttpEntity,
                    Void.class
            );

            if (!response.getStatusCode().is2xxSuccessful()) {
                throw new ResponseDtoException("해당 태스크의 태그 삭제에 실패했습니다.");
            }
        }
        catch(Exception e) {
            throw new ResponseDtoException("서버에서 프로젝트 태그 테이블 삭제 요청에 대한 응답을 받지 못했습니다.");
        }


    }

}
