package com.nhnacademy.gateway.model.dto.task;

import com.nhnacademy.gateway.model.dto.milestone.ResponseMilestoneDto;
import com.nhnacademy.gateway.model.dto.tag.ResponseTagDto;
import com.nhnacademy.gateway.model.dto.tag.ResponseTagListByTaskDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ResponseTaskDetailDto {
    // TODO Task 목록 반환할 때 모든 값을 보여줘야 함 결국 이 값으로 반환해야 목록이 다 보여짐
    private long taskId;
    private String taskTitle;
    private String taskDescription;
    private ResponseMilestoneDto milestone;
    private ResponseTagListByTaskDto tag;
}
