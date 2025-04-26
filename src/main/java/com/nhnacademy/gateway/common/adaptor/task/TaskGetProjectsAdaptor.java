package com.nhnacademy.gateway.common.adaptor.task;

import com.nhnacademy.gateway.exception.ResponseDtoException;
import com.nhnacademy.gateway.model.dto.ResponseProjectsDto;
import com.nhnacademy.gateway.model.request.member.MemberIdRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * memberId 값에 대한 Project 리스트를 가져오는 Adaptor
 */
@Slf4j
@Component
public class TaskGetProjectsAdaptor {
    private static final String TASK_API_URL = "http://localhost:7070/project/";

    private RestTemplate restTemplate;

    public TaskGetProjectsAdaptor() {
        this.restTemplate = new RestTemplate();
    }

    public ResponseProjectsDto sendAndGetProjects(MemberIdRequest memberIdRequest) {

        try {
            ResponseEntity<ResponseProjectsDto> response = restTemplate.getForEntity(TASK_API_URL + "/" +"/members"+ memberIdRequest.getMemberId(), ResponseProjectsDto.class);

            if (!response.getStatusCode().is2xxSuccessful()) {
                throw new ResponseDtoException("Project 리스트를 가져오지 못했습니다.");
            }

            return response.getBody();
        }
        catch(Exception e) {
            throw new ResponseDtoException("Project 리스트를 가져오지 못했습니다.");
        }
    }

}
