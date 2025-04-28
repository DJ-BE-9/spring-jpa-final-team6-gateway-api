package com.nhnacademy.gateway.model.request.task;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class RegisterTaskRequest {
    private String taskTitle;
    private String taskDescription;
    private Long milestoneId;
}
