package com.nhnacademy.gateway.model.request.task;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TaskDetailRequest {

    private long taskId;
    private String taskTitle;
    private String taskDescription;
    private long milestoneId;

}
