package com.nhnacademy.gateway.model.domain;

import com.nhnacademy.gateway.model.type.State;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Project {

    private long projectId;
    private String projectName;
    private State projectState;

}
