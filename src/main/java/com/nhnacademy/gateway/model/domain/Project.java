package com.nhnacademy.gateway.model.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
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
//    @JsonProperty("project_id")
    private long projectId;

//    @JsonProperty("project_name")
    private String projectName;

//    @JsonProperty("project_state")
    private State projectState;

}
