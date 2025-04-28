package com.nhnacademy.gateway.common.adaptor.comment;

import com.nhnacademy.gateway.exception.ResponseDtoException;
import com.nhnacademy.gateway.model.dto.comment.ResponseCommentDto;
import com.nhnacademy.gateway.model.dto.comment.ResponseCommentListDto;
import com.nhnacademy.gateway.model.request.comment.RegisterCommentRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Objects;

@Component
public class CommentGetCommentsAdaptor {
    private static final String COMMENT_GET_URL = "http://localhost:7070/project/";

    private RestTemplate restTemplate;

    public CommentGetCommentsAdaptor(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public ResponseCommentListDto getComments(long projectId, long taskId) {

        try {
            String url = COMMENT_GET_URL + String.valueOf(projectId) + "/task/" + String.valueOf(taskId) + "/comment";
            ResponseEntity<ResponseCommentListDto> response = restTemplate.getForEntity(url, ResponseCommentListDto.class);

            if(!response.getStatusCode().is2xxSuccessful()) {
                throw new ResponseDtoException("Task ID 값에 대한 Comment들을 가져오지 못했습니다.");
            }

            return response.getBody();
        }
        catch(Exception e) {
            throw new ResponseDtoException("Comments 값들을 가져오지 못했습니다.");
        }

    }


}
