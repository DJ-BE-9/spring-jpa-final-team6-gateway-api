package com.nhnacademy.gateway.model.dto.task;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ResponseTaskDto {
    private long taskId;
    private String taskTitle;
    private String taskDescription;
    private long milestoneId;
}
