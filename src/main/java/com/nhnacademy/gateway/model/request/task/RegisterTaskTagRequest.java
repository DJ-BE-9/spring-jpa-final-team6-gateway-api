package com.nhnacademy.gateway.model.request.task;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class RegisterTaskTagRequest {
    @NotNull
    private String taskTitle;
    @NotNull
    private String taskDescription;
    private Long milestoneId;
    private List<Long> tagIds;
}
