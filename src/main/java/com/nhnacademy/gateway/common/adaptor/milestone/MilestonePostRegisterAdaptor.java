package com.nhnacademy.gateway.common.adaptor.milestone;

import com.nhnacademy.gateway.exception.ResponseDtoException;
import com.nhnacademy.gateway.model.dto.milestone.ResponseMilestoneDto;
import com.nhnacademy.gateway.model.request.milestone.RegisterMilestoneRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;

@Component
@RequiredArgsConstructor
@Slf4j
public class MilestonePostRegisterAdaptor {

    private static final String PROJECT_API = "http://localhost:7070/project/";

    private final RestTemplate restTemplate;

    public ResponseMilestoneDto sendRegisterRequest(long projectId, RegisterMilestoneRequest registerMilestoneRequest) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        String api = PROJECT_API + projectId + "/milestone";

        HttpEntity<RegisterMilestoneRequest> requestHttpEntity = new HttpEntity<>(registerMilestoneRequest, headers);

        try {
            ResponseEntity<ResponseMilestoneDto> response = restTemplate.postForEntity(api, requestHttpEntity, ResponseMilestoneDto.class);
            log.info("{}", projectId);
            log.info("{}", response.getBody().getMilestoneName());
            log.info("{}", response.getBody().getMilestoneStartedAt());
            log.info("{}", response.getBody().getMilestoneEndedAt());
            if(!response.getStatusCode().is2xxSuccessful() || Objects.isNull(response.getBody())) {
                throw new ResponseDtoException("마일스톤을 등록하지 못했습니다.");
            }

            return response.getBody();
        }
        catch(Exception e) {
            throw new ResponseDtoException("서버에서 해당 요청의 응답을 받지 못했습니다");
        }

    }


}
