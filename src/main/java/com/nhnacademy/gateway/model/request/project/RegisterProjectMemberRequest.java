package com.nhnacademy.gateway.model.request.project;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RegisterProjectMemberRequest {
    private String userId;
    private boolean projectManager;
}
