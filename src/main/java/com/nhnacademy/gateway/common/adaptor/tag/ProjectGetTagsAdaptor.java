package com.nhnacademy.gateway.common.adaptor.tag;

import com.nhnacademy.gateway.exception.ResponseDtoException;
import com.nhnacademy.gateway.model.dto.tag.ResponseTagsDto;
import com.nhnacademy.gateway.model.dto.tag.TagRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/*
프로젝트에 등록된 Tag 가져오기
 */
@Slf4j
@Component
public class ProjectGetTagsAdaptor {
    private static final String PROJECT_API_URL = "http://localhost:7070/project/";

    private final RestTemplate restTemplate;

    public ProjectGetTagsAdaptor(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public ResponseTagsDto sendAndGetProjectTags(TagRequest tagRequest) {
        try{
            String url = PROJECT_API_URL + tagRequest.getProjectId() + "/tag";
            log.info(url);
            ResponseEntity<ResponseTagsDto> response = restTemplate.getForEntity(url, ResponseTagsDto.class);
            log.info("response from task : {}",response);
            if (!response.getStatusCode().is2xxSuccessful()) {
                throw new ResponseDtoException("Project에 등록된 Tag 리스트를 가져오지 못했습니다.");
            }
            return response.getBody();
        }catch(Exception e) {
            log.error("try-catch: {}", e, e);
            throw new ResponseDtoException("Project에 등록된 Tag 리스트를 가져오던 중 에러 발생");
        }
    }
}