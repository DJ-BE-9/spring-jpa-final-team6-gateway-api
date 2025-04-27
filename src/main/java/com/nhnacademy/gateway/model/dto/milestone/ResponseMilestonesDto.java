package com.nhnacademy.gateway.model.dto.milestone;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResponseMilestonesDto {
    List<ResponseMilestoneDto> milestones;
}
