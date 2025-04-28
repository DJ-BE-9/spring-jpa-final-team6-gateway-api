package com.nhnacademy.gateway.common.adaptor.project;

import com.nhnacademy.gateway.model.dto.ResponseDto;
import com.nhnacademy.gateway.model.request.member.MemberIdRequest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class ProjectPostProjectMemberDeleteAdaptor {
    private static final String PROJECT_MEMBER_DELETE_API_URL = "http://localhost:7070/project/";

    private RestTemplate restTemplate;

    public ProjectPostProjectMemberDeleteAdaptor(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public boolean deleteProjectMember(long projectId, String memberId) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<MemberIdRequest> requestHttpEntity = new HttpEntity<>(new MemberIdRequest(memberId), headers);

        try {
            String url = PROJECT_MEMBER_DELETE_API_URL + String.valueOf(projectId) + "/members/" + String.valueOf(memberId);
            ResponseEntity<ResponseDto> response = restTemplate.postForEntity(url, requestHttpEntity, ResponseDto.class);

            return response.getStatusCode().is2xxSuccessful();
        }
        catch(Exception e) {
            return false;
        }

    }


}
