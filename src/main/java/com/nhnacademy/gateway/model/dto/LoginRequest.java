package com.nhnacademy.gateway.model.dto;

import lombok.Getter;
import lombok.NonNull;
import lombok.Value;

@Getter
public class LoginRequest {

    @NonNull
    String memberId;

    @NonNull
    String password;

}
