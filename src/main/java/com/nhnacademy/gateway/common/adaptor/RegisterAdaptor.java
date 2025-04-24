package com.nhnacademy.gateway.common.adaptor;

import com.nhnacademy.gateway.model.dto.RegisterRequest;
import com.nhnacademy.gateway.model.dto.ResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Component
public class RegisterAdaptor {
    private static final String REGISTER_API_URL = "http://localhost:9090/account/member";

    private RestTemplate restTemplate;

    public RegisterAdaptor() {
        restTemplate = new RestTemplate();
    }

    public boolean sendRegisterRequest(RegisterRequest registerRequest) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<RegisterRequest> requestHttpEntity = new HttpEntity<>(registerRequest, headers);

        try {
            ResponseEntity<ResponseDto> response = restTemplate.postForEntity(REGISTER_API_URL, requestHttpEntity, ResponseDto.class);

            return response.getStatusCode().is2xxSuccessful();
        } catch (Exception e) {
            return false;
        }

    }

}
