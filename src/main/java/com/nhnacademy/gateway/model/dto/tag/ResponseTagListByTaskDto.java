package com.nhnacademy.gateway.model.dto.tag;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ResponseTagListByTaskDto {
    List<ResponseTagIdDto> tagList;
}
