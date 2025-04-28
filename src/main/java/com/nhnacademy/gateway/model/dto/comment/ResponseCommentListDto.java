package com.nhnacademy.gateway.model.dto.comment;

import com.nhnacademy.gateway.model.request.comment.RegisterCommentRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResponseCommentListDto {

    List<RegisterCommentRequest> comments;

}
