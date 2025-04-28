package com.nhnacademy.gateway.model.request.project;

import com.nhnacademy.gateway.model.type.State;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProjectStateRequest {

    private long projectId;
    private State projectState;

}
