package com.nhnacademy.gateway.model.dto;

import com.nhnacademy.gateway.model.request.project.ProjectMember;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResponseProjectMembersDto {

    List<ProjectMember> members;

}
