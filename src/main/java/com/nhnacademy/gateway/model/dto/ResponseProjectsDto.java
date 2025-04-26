package com.nhnacademy.gateway.model.dto;

import com.nhnacademy.gateway.model.domain.Project;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResponseProjectsDto {

    private List<Project> projects;

}
