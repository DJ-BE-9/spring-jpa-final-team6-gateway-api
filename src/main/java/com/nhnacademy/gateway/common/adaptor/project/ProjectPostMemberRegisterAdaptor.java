package com.nhnacademy.gateway.common.adaptor.project;

import com.nhnacademy.gateway.model.dto.ResponseDto;
import com.nhnacademy.gateway.model.request.project.RegisterProjectMemberRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@Slf4j
@RequiredArgsConstructor
public class ProjectPostMemberRegisterAdaptor {
    private static final String REGISTER_MEMBER_URL = "http://localhost:7070/project/";

    private final RestTemplate restTemplate; //.

    public boolean sendRegisterProjectMember(long projectId, RegisterProjectMemberRequest registerProjectMemberRequest) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<RegisterProjectMemberRequest> requestHttpEntity = new HttpEntity<>(registerProjectMemberRequest, headers);
        log.info("{}", registerProjectMemberRequest.getMemberId());
        try {
            String url = REGISTER_MEMBER_URL + projectId + "/members";
            ResponseEntity<ResponseDto> response = restTemplate.postForEntity(url, requestHttpEntity, ResponseDto.class);

            return response.getStatusCode().is2xxSuccessful();
        }
        catch (Exception e) {
            return false;
        }

    }


}
