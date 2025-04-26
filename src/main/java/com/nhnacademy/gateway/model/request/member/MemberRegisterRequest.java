package com.nhnacademy.gateway.model.request.member;

import jakarta.validation.constraints.Email;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MemberRegisterRequest {

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
