package com.nhnacademy.gateway.model.request.comment;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RegisterCommentRequest {

    String commentContent;
    long taskId;
    String commentWriterId;

}
