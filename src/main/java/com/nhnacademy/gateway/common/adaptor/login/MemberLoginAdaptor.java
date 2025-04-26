package com.nhnacademy.gateway.common.adaptor.login;

import com.nhnacademy.gateway.exception.LoginProcessException;
import com.nhnacademy.gateway.model.request.member.MemberLoginRequest;
import com.nhnacademy.gateway.model.dto.ResponseLoginDto;
import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpHeaders;

import java.util.Objects;

@Component
public class MemberLoginAdaptor {
    private static final String LOGIN_API_URL = "http://localhost:9090/account/login";

    private RestTemplate restTemplate;

    public MemberLoginAdaptor() {
        restTemplate = new RestTemplate();
    }

    public ResponseLoginDto sendLoginRequest(MemberLoginRequest loginRequest) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<MemberLoginRequest> requestHttpEntity = new HttpEntity<>(loginRequest, headers);

        try {
            ResponseEntity<ResponseLoginDto> response = restTemplate.postForEntity(LOGIN_API_URL, requestHttpEntity, ResponseLoginDto.class);

            if(!response.getStatusCode().is2xxSuccessful() || Objects.isNull(response.getBody())) {
                throw new LoginProcessException("로그인 실패");
            }

            return response.getBody();
        }
        catch(Exception e) {
            throw new LoginProcessException("로그인 실패");
        }

    }

}
