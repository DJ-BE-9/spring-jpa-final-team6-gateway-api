package com.nhnacademy.gateway.model.dto;

import jakarta.validation.constraints.Email;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest {

    @NonNull
    String memberId;

    @NonNull
    String password;

    @NonNull
    @Email
    String email;

    @NonNull
    String name;

}
