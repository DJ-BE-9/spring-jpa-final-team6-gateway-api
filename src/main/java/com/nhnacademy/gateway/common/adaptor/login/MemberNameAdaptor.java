package com.nhnacademy.gateway.common.adaptor.login;

import com.nhnacademy.gateway.exception.ResponseDtoException;
import com.nhnacademy.gateway.model.dto.MemberIdRequest;
import com.nhnacademy.gateway.model.dto.ResponseUserNameDto;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;

@Component
public class MemberNameAdaptor {
    private static final String ACCOUNT_API_USERNAME_URL = "http://localhost:9090/account/name";

    private RestTemplate restTemplate;

    public MemberNameAdaptor() {
        this.restTemplate = new RestTemplate();
    }

    public ResponseUserNameDto sendResponseUserNameDto(MemberIdRequest memberIdRequest) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<MemberIdRequest> requestHttpEntity = new HttpEntity<>(memberIdRequest, headers);

        try {
            ResponseEntity<ResponseUserNameDto> response = restTemplate.postForEntity(ACCOUNT_API_USERNAME_URL, requestHttpEntity, ResponseUserNameDto.class);

            if(!response.getStatusCode().is2xxSuccessful() || Objects.isNull(response)) {
                throw new ResponseDtoException("Member Name 값을 가져오지 못했습니다.");
            }

            return response.getBody();
        }
        catch(Exception e) {
            throw new ResponseDtoException("Member Name 값을 가져오지 못했습니다.");
        }

    }

}
