package com.nhnacademy.gateway.model.dto.comment;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResponseCommentDto {

    private String commentContent;
    private long taskId;
    private String commentWriterId;

}
