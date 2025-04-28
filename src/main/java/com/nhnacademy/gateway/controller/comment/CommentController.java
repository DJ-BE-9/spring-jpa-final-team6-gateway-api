package com.nhnacademy.gateway.controller.comment;

import com.nhnacademy.gateway.exception.ValidationFailedException;
import com.nhnacademy.gateway.model.request.comment.CommentContentRequest;
import com.nhnacademy.gateway.model.request.comment.RegisterCommentRequest;
import com.nhnacademy.gateway.service.CommentService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/project/{projectId}/task/{taskId}")
public class CommentController {

    @Autowired
    private StringRedisTemplate stringStringRedisTemplate;
    @Autowired
    private CommentService commentService;

    @PostMapping("/comment")
    public String postTaskComment(HttpServletRequest request,
                                  Model model,
                                  @PathVariable("projectId") long projectId,
                                  @PathVariable("taskId") long taskId,
                                  @Validated @ModelAttribute CommentContentRequest commentContentRequest,
                                  BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            throw new ValidationFailedException(bindingResult);
        }

        String sessionId = getSessionIdFromCookie(request);

        String memberId = stringStringRedisTemplate.opsForValue().get(sessionId);
        if (memberId != null) {
            model.addAttribute("memberId", memberId);
        }

        commentService.postComment(commentContentRequest, projectId, taskId, memberId);

        return "redirect:/localhost:8080/project/" + String.valueOf(projectId) + "/task/" + String.valueOf(taskId);

    }

    private String getSessionIdFromCookie(HttpServletRequest request) {
        if(request.getCookies() != null) {
            for(Cookie cookie : request.getCookies()) {
                if(cookie.getName().equals("SESSIONID")) {
                    return cookie.getValue();
                }
            }
        }

        return null;
    }

}
