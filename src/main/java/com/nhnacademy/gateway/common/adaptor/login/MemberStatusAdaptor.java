package com.nhnacademy.gateway.common.adaptor.login;

import com.nhnacademy.gateway.exception.ResponseDtoException;
import com.nhnacademy.gateway.model.dto.MemberCudRequest;
import com.nhnacademy.gateway.model.dto.MemberIdRequest;
import com.nhnacademy.gateway.model.dto.ResponseDto;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;

@Component
public class MemberStatusAdaptor {
    private static final String ACCOUNT_API_MEMBER_STATUS_URL = "http://localhost:9090/account/status";

    private RestTemplate restTemplate;

    public MemberStatusAdaptor() {
        this.restTemplate = new RestTemplate();
    }

    public ResponseDto sendMemberStatusRequest(MemberCudRequest memberCudRequest) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<MemberCudRequest> requestHttpEntity = new HttpEntity<>(memberCudRequest, headers);

        try {
            ResponseEntity<ResponseDto> response = restTemplate.postForEntity(ACCOUNT_API_MEMBER_STATUS_URL, requestHttpEntity, ResponseDto.class);

            if(!response.getStatusCode().is2xxSuccessful() || Objects.isNull(response)) {
                throw new ResponseDtoException("Member 상태를 바꾸지 못했습니다.");
            }

            return response.getBody();
        }
        catch(Exception e) {
            throw new ResponseDtoException("Member 상태를 바꾸지 못했습니다.");
        }

    }

}
