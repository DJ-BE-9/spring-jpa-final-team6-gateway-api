package com.nhnacademy.gateway.common.adaptor.tag;

import com.nhnacademy.gateway.exception.ResponseDtoException;
import com.nhnacademy.gateway.model.dto.tag.ResponseTagDto;
import com.nhnacademy.gateway.model.dto.tag.ResponseTagsDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/*
프로젝트에 등록된 Tag 가져오기
 */
@Slf4j
@Component
public class ProjectGetTagAdapter {
    private static final String PROJECT_API_URL = "http://localhost:7070/project/";

    private final RestTemplate restTemplate;

    public ProjectGetTagAdapter(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public ResponseTagsDto sendAndGetProjectTags(String projectId) {
        try{
            String url = PROJECT_API_URL + projectId + "/tag";
            log.info(url);
            ResponseEntity<ResponseTagsDto> response = restTemplate.getForEntity(PROJECT_API_URL + projectId + "/tag", ResponseTagsDto.class);
//            List<ResponseTagDto> Tags = response.getBody().getTags();

        }catch(Exception e) {
            log.error("try-catch: {}", e, e);
            throw new ResponseDtoException("Project에 등록된 Tag 리스트를 가져오지 못했습니다.");
        }
        return null; //TODO
    }
}


/*

    public ResponseProjectsDto sendAndGetProjects(MemberIdRequest memberIdRequest) {

        try {
            ResponseEntity<ResponseProjectsDto> response = restTemplate.getForEntity(PROJECT_API_URL  +"members/"+ memberIdRequest.getMemberId(), ResponseProjectsDto.class);
            List<Project> projects = response.getBody().getProjects();
            if (!response.getStatusCode().is2xxSuccessful()) {
                throw new ResponseDtoException("Project 리스트를 가져오지 못했습니다.");
            }

            return response.getBody();
        } catch(Exception e) {
            log.error("try-catch: {}", e, e);
            throw new ResponseDtoException("Project 리스트를 가져오지 못했습니다.");
        }
    }

}
 */
