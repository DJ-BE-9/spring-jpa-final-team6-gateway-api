package com.nhnacademy.gateway.common.adaptor.task;

import com.nhnacademy.gateway.exception.ResponseDtoException;
import com.nhnacademy.gateway.model.dto.task.ResponseTaskDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;

@Component
public class TaskGetTaskDetailAdaptor {
    private static final String TASK_DETAIL_URL = "http://localhost:7070/project/";

    private RestTemplate restTemplate;

    public TaskGetTaskDetailAdaptor(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public ResponseTaskDto getTaskRequest(long projectId, long taskId) {

        try {
            String url = TASK_DETAIL_URL + String.valueOf(projectId) + "/task/" + String.valueOf(taskId);
            ResponseEntity<ResponseTaskDto> response = restTemplate.getForEntity(url, ResponseTaskDto.class);

            if(Objects.isNull(response) || !response.getStatusCode().is2xxSuccessful()) {
                throw new ResponseDtoException("Task 값들을 가져오지 못했습니다.");
            }

            return response.getBody();
        }
        catch(Exception e) {
            throw new ResponseDtoException("Task 값들을 가져오지 못했습니다.");
        }

    }

}
