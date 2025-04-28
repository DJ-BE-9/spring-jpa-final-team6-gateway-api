package com.nhnacademy.gateway.service;

import com.nhnacademy.gateway.common.adaptor.comment.CommentGetCommentsAdaptor;
import com.nhnacademy.gateway.common.adaptor.comment.CommentPostAdaptor;
import com.nhnacademy.gateway.exception.EmptyRequestException;
import com.nhnacademy.gateway.model.request.comment.CommentContentRequest;
import com.nhnacademy.gateway.model.request.comment.RegisterCommentRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Slf4j
@Service
@Transactional
public class CommentService {

    @Autowired
    private CommentGetCommentsAdaptor commentGetCommentsAdaptor;
    @Autowired
    private CommentPostAdaptor commentPostAdaptor;

    public List<RegisterCommentRequest> getComments(long projectId, long taskId) {
        if(taskId < 0) {
            throw new EmptyRequestException("task ID 값을 받지 못했습니다.");
        }

        return commentGetCommentsAdaptor.getComments(projectId, taskId);
    }

    public void postComment(CommentContentRequest commentContentRequest, long projectId, long taskId, String memberId) {
        if(Objects.isNull(commentContentRequest)) {
            throw new EmptyRequestException("Comment 내용이 비어있습니다.");
        }

        if(projectId < 0 || taskId < 0) {
            throw new EmptyRequestException("ID 값이 제대로 전달되지 않았습니다.");
        }

        commentPostAdaptor.sendAndRegisterComment(new RegisterCommentRequest(commentContentRequest.getCommentContent(), taskId, memberId), projectId);
    }

}
