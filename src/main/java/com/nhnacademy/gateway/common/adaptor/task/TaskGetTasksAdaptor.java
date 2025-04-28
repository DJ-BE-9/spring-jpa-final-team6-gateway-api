package com.nhnacademy.gateway.common.adaptor.task;

import com.nhnacademy.gateway.exception.ResponseDtoException;
import com.nhnacademy.gateway.model.dto.task.ResponseTaskListDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@RequiredArgsConstructor
@Slf4j
public class TaskGetTasksAdaptor {

    private static final String PROJECT_API = "http://localhost:7070/project/";

    private final RestTemplate restTemplate;

    public ResponseTaskListDto sendGetTaskListRequest(long projectId) {

        String api = PROJECT_API + projectId + "/task";

        try {
            ResponseEntity<ResponseTaskListDto> response = restTemplate.getForEntity(api, ResponseTaskListDto.class);
            ResponseTaskListDto responseTaskDto = response.getBody();
            if(!response.getStatusCode().is2xxSuccessful()) {
                throw new ResponseDtoException("태스크를 등록하지 못했습니다.");
            }
            return responseTaskDto;
        }
        catch(Exception e) {
            throw new ResponseDtoException("서버에서 해당 태스크 등록 요청 응답을 받지 못했습니다");
        }

    }

}
