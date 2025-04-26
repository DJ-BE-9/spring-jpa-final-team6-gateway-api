package com.nhnacademy.gateway.model.request.member;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MemberLoginRequest {

    @NonNull
    String memberId;

}
