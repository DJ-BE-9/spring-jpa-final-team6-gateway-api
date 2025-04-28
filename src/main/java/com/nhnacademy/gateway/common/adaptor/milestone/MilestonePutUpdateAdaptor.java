package com.nhnacademy.gateway.common.adaptor.milestone;

import com.nhnacademy.gateway.exception.ResponseDtoException;
import com.nhnacademy.gateway.model.dto.milestone.ResponseMilestoneDto;
import com.nhnacademy.gateway.model.request.milestone.RegisterMilestoneRequest;
import com.nhnacademy.gateway.model.request.milestone.UpdateMilestoneRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;

@Component
@RequiredArgsConstructor
@Slf4j
public class MilestonePutUpdateAdaptor {

    private static final String PROJECT_API = "http://localhost:7070/project/";

    private final RestTemplate restTemplate;

    public ResponseMilestoneDto sendUpdateRequest(long projectId, long milestoneId, UpdateMilestoneRequest updateMilestoneRequest) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        String api = PROJECT_API + projectId + "/milestone/" + milestoneId;

        HttpEntity<UpdateMilestoneRequest> requestHttpEntity = new HttpEntity<>(updateMilestoneRequest, headers);

        try {
            ResponseEntity<ResponseMilestoneDto> response = restTemplate.exchange(
                    api,
                    HttpMethod.PUT,
                    requestHttpEntity,
                    ResponseMilestoneDto.class
            );

            if(!response.getStatusCode().is2xxSuccessful() || Objects.isNull(response.getBody())) {
                throw new ResponseDtoException("마일스톤을 수정하지 못했습니다.");
            }

            return response.getBody();
        }
        catch(Exception e) {
            throw new ResponseDtoException("서버에서 해당 요청의 응답을 받지 못했습니다");
        }

    }
}
