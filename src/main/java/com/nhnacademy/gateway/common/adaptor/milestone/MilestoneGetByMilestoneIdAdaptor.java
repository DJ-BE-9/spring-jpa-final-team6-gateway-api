package com.nhnacademy.gateway.common.adaptor.milestone;

import com.nhnacademy.gateway.exception.ResponseDtoException;
import com.nhnacademy.gateway.model.dto.milestone.ResponseMilestoneDto;
import com.nhnacademy.gateway.model.dto.milestone.ResponseMilestonesDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;

@Component
@RequiredArgsConstructor
@Slf4j
public class MilestoneGetByMilestoneIdAdaptor {

    private static final String PROJECT_API = "http://localhost:7070/project/";

    private final RestTemplate restTemplate;

    public ResponseMilestoneDto sendGetMilestonesRequest(long projectId) {
        String api = PROJECT_API + projectId + "/milestone";

        try {
            ResponseEntity<ResponseMilestoneDto> response = restTemplate.getForEntity(api, ResponseMilestoneDto.class);

            if (!response.getStatusCode().is2xxSuccessful() || Objects.isNull(response.getBody())) {
                throw new ResponseDtoException("해당 프로젝트의 마일스톤 목록을 반환하지 못했습니다.");
            }

            return response.getBody();
        } catch (Exception e) {
            throw new ResponseDtoException("서버에서 해당 프로젝트의 마일스톤 목록 요청의 응답을 받지 못했습니다");
        }


    }
}