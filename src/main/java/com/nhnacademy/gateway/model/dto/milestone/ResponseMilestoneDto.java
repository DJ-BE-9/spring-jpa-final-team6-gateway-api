package com.nhnacademy.gateway.model.dto.milestone;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.ZonedDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResponseMilestoneDto {
    private Long milestoneId;
    private String milestoneName;
    private ZonedDateTime milestoneStartedAt;
    private ZonedDateTime milestoneEndedAt;
}
