package com.nhnacademy.gateway.common.adaptor.tag;

import com.nhnacademy.gateway.model.dto.ResponseDto;
import com.nhnacademy.gateway.model.dto.tag.TagUpdateRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Component
public class TagUpdateAdapter {
    private static final String PROJECT_API_URL = "http://localhost:7070/project/";
    private final RestTemplate restTemplate;

    public TagUpdateAdapter(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public void sendUpdateRequest(long tagId, TagUpdateRequest tagUpdateRequest, long projectId) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<TagUpdateRequest> request = new HttpEntity<>(tagUpdateRequest, headers);

        try {
            String url = PROJECT_API_URL + projectId + "/tag/" + tagId;
            log.info("Tag 수정{}", url);
            ResponseEntity<Void> response = restTemplate.exchange(url,
                    HttpMethod.PUT,
                    request,
                    Void.class);
            response.getStatusCode().is2xxSuccessful();
        } catch (Exception e) {
            log.error("Tag 수정 요청 실패",e);
        }
    }
}
