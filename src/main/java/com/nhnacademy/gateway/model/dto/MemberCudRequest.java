package com.nhnacademy.gateway.model.dto;

import com.nhnacademy.gateway.model.type.Cud;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MemberCudRequest {

    private String memberId;
    private Cud cud;

}
