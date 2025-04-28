package com.nhnacademy.gateway.common.adaptor.project;

import com.nhnacademy.gateway.exception.ResponseDtoException;
import com.nhnacademy.gateway.model.dto.ResponseDto;
import com.nhnacademy.gateway.model.dto.ResponseIsTrueDto;
import com.nhnacademy.gateway.model.request.member.MemberIdRequest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;

@Component
public class ProjectFindMemberAdaptor {
    private static final String PROJECT_FIND_MEMBER_URL = "http://localhost:9090/project/";

    private final RestTemplate restTemplate;

    public ProjectFindMemberAdaptor(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public boolean sendAccountExistsMember(MemberIdRequest memberIdRequest, Long projectId) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<MemberIdRequest> requestHttpEntity = new HttpEntity<>(memberIdRequest, headers);

        try {
            String url = PROJECT_FIND_MEMBER_URL + String.valueOf(projectId) + "/members";
            ResponseEntity<ResponseIsTrueDto> response = restTemplate.postForEntity(url, requestHttpEntity, ResponseIsTrueDto.class);

            if(Objects.isNull(response.getBody())) {
                throw new ResponseDtoException("ID 값에 해당하는 Member를 찾지 못했습니다.");
            }

            return response.getStatusCode().is2xxSuccessful();
        }
        catch(Exception e) {
            throw new ResponseDtoException("ID 값에 해당하는 Member를 찾지 못했습니다.");
        }

    }

}
