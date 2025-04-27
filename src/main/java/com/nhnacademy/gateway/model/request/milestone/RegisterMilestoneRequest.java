package com.nhnacademy.gateway.model.request.milestone;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.ZonedDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RegisterMilestoneRequest {
    private String milestoneName;
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private ZonedDateTime milestoneStartedAt;
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private ZonedDateTime milestoneEndedAt;
}
