package com.nhnacademy.gateway.common.adaptor.project;

import com.nhnacademy.gateway.exception.ResponseDtoException;
import com.nhnacademy.gateway.model.dto.ResponseDto;
import com.nhnacademy.gateway.model.request.member.MemberIdRequest;
import com.nhnacademy.gateway.model.request.project.RegisterProjectMemberRequest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;

@Component
public class ProjectRegisterMemberAdaptor {

    private static final String PROJECT_MEMBER_API_URL = "http://localhost:7070/project/";

    private final RestTemplate restTemplate;

    public ProjectRegisterMemberAdaptor(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public ResponseDto sendRegisterMember(RegisterProjectMemberRequest registerProjectMemberRequest, long projectId) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<RegisterProjectMemberRequest> requestHttpEntity = new HttpEntity<>(registerProjectMemberRequest, headers);

        try {
            String url = PROJECT_MEMBER_API_URL + String.valueOf(projectId) + "/members";
            ResponseEntity<ResponseDto> response = restTemplate.postForEntity(url, requestHttpEntity, ResponseDto.class);

            if(!response.getStatusCode().is2xxSuccessful() || Objects.isNull(response.getBody())) {
                throw new ResponseDtoException("팀원을 추가하지 못했습니다.");
            }

            return response.getBody();
        }
        catch(Exception e) {
            throw new ResponseDtoException("팀원을 추가하지 못했습니다.");
        }

    }

}
