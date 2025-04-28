package com.nhnacademy.gateway.model.dto.tag;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TagUpdateRequest {
//    private long tagId;
    private String tagName;
}
