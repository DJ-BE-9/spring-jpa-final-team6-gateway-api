package com.nhnacademy.gateway.model.dto.task;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ResponseTaskListDto {
    List<ResponseTaskDto> tasks = new ArrayList<>();
}
