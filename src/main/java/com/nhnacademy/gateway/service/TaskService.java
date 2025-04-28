package com.nhnacademy.gateway.service;

import com.nhnacademy.gateway.common.adaptor.projectTag.ProjectTagPostRegisterAdaptor;
import com.nhnacademy.gateway.common.adaptor.task.TaskGetTasksAdaptor;
import com.nhnacademy.gateway.common.adaptor.task.TaskPostRegisterAdaptor;
import com.nhnacademy.gateway.common.adaptor.task.TaskPutUpdateAdaptor;
import com.nhnacademy.gateway.exception.EmptyRequestException;
import com.nhnacademy.gateway.exception.RegisterProcessException;
import com.nhnacademy.gateway.model.dto.milestone.ResponseMilestoneDto;
import com.nhnacademy.gateway.model.dto.tag.ResponseTagDto;
import com.nhnacademy.gateway.model.dto.task.ResponseTaskDetailDto;
import com.nhnacademy.gateway.model.dto.task.ResponseTaskDto;
import com.nhnacademy.gateway.model.request.projectTag.RegisterProjectTagRequest;
import com.nhnacademy.gateway.model.request.task.RegisterTaskRequest;
import com.nhnacademy.gateway.model.request.task.RegisterTaskTagRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Slf4j
@Service
@Transactional
public class TaskService {
    // 태스크 등록 요청 어댑터
    @Autowired
    private TaskPostRegisterAdaptor taskPostRegisterAdaptor;
    @Autowired
    private ProjectTagPostRegisterAdaptor projectTagPostRegisterAdaptor;

    // 태스크 목록 요청 어댑터
    @Autowired
    private TaskGetTasksAdaptor taskGetTasksAdaptor;

    // 태스크 수정 요청 어댑터
    @Autowired
    private TaskPutUpdateAdaptor taskPutUpdateAdaptor;

    // 태스크 삭제 요청 어댑터

    @Autowired
    private MilestoneService milestoneService;

    public void registerTask(long projectId, RegisterTaskTagRequest request) {
        if(Objects.isNull(request)) {
            throw new EmptyRequestException("RegisterTaskRequest 값을 받지 못했습니다");
        }
        RegisterTaskRequest registerTaskRequest = new RegisterTaskRequest(request.getTaskTitle(), request.getTaskDescription(), request.getMilestoneId());
        long taskId = taskPostRegisterAdaptor.sendRegisterRequest(projectId, registerTaskRequest);
        RegisterProjectTagRequest registerProjectTagRequest = new RegisterProjectTagRequest(request.getTagIds());
        if(!projectTagPostRegisterAdaptor.sendRegisterRequest(projectId, taskId, registerProjectTagRequest)) {
            throw new RegisterProcessException("해당 Task에 Tag를 등록하지 못했습니다.");
        }
    }

    public List<ResponseTaskDetailDto> getTaskByProjectId(long projectId) {
        List<ResponseTaskDetailDto> taskDetailDtoList = new ArrayList<>();

        List<ResponseTaskDto> taskDtoList = taskGetTasksAdaptor.sendGetTaskListRequest(projectId).getTasks();
        for(ResponseTaskDto taskDto : taskDtoList ) {
            ResponseMilestoneDto milestone = milestoneService.getMilestone(taskDto.getMilestoneId());
            ResponseTaskDetailDto responseTaskDetailDto = new ResponseTaskDetailDto(
                    taskDto.getTaskId(),
                    taskDto.getTaskTitle(),
                    taskDto.getTaskDescription(),
                    milestone,
                    new ResponseTagDto("태그명")
            );
            taskDetailDtoList.add(responseTaskDetailDto);
        }

        return taskDetailDtoList;
    }



}
