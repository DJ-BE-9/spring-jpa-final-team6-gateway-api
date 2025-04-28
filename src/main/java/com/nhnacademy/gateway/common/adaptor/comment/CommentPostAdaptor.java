package com.nhnacademy.gateway.common.adaptor.comment;

import com.nhnacademy.gateway.exception.ResponseDtoException;
import com.nhnacademy.gateway.model.dto.ResponseDto;
import com.nhnacademy.gateway.model.dto.comment.ResponseCommentDto;
import com.nhnacademy.gateway.model.request.comment.RegisterCommentRequest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;

@Component
public class CommentPostAdaptor {
    private static final String COMMENT_POST_URL = "http://localhost:7070/project/";

    private RestTemplate restTemplate;

    public CommentPostAdaptor(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public boolean sendAndRegisterComment(RegisterCommentRequest registerCommentRequest, long projectId) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<RegisterCommentRequest> requestHttpEntity = new HttpEntity<>(registerCommentRequest, headers);

        try {
            String url = COMMENT_POST_URL + String.valueOf(projectId) + "/task/" + String.valueOf(registerCommentRequest.getTaskId()) + "/comment";
            ResponseEntity<ResponseCommentDto> response = restTemplate.postForEntity(url, requestHttpEntity, ResponseCommentDto.class);

            if(Objects.isNull(response)) {
                throw new ResponseDtoException("Comment를 입력하지 못했습니다.");
            }

            return response.getStatusCode().is2xxSuccessful();
        }
        catch(Exception e) {
            return false;
        }

    }

}
