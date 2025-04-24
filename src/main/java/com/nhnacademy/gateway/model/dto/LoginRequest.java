package com.nhnacademy.gateway.model.dto;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequest {

    @NonNull
    String memberId;

}
