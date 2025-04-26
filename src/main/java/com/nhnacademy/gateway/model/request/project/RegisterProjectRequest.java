package com.nhnacademy.gateway.model.request.project;

import com.nhnacademy.gateway.model.type.State;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RegisterProjectRequest {

    @NonNull
    private String projectName;
    @NonNull
    private State projectState;

}
