package com.nhnacademy.gateway.common.adaptor.tag;

import com.nhnacademy.gateway.model.dto.ResponseDto;
import com.nhnacademy.gateway.model.dto.tag.TagDeleteRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Component
public class TagDeleteAdapter {
    private static final String PROJECT_API_URL = "http://localhost:7070/project/";
    private final RestTemplate restTemplate;

    public TagDeleteAdapter(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public void sendDeleteRequest(long tagId, long projectId) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Long> request = new HttpEntity<>(tagId, headers);

        try {
            String url = PROJECT_API_URL + projectId + "/tag/" + tagId;
            log.info(url);
            ResponseEntity<Void> response = restTemplate.exchange(url,
                    HttpMethod.DELETE,
                    request,
                    Void.class);
            response.getStatusCode().is2xxSuccessful();
        } catch (Exception e) {
            log.error("Tag 삭제 요청 실패",e);
        }
    }
}
