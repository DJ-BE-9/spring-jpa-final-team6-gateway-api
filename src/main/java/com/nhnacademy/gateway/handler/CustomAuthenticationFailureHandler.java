package com.nhnacademy.gateway.handler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import java.io.IOException;
import java.net.URLEncoder;

public class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request,
                                        HttpServletResponse response,
                                        AuthenticationException exception) throws IOException, ServletException {

        String errorMessage = "아이디 또는 비밀번호가 틀렸습니다.";

        if (exception.getMessage().equals("탈퇴한 회원입니다.")) {
            errorMessage = "탈퇴한 회원입니다.";
        }

        response.sendRedirect("/login?error=" + URLEncoder.encode(errorMessage, "UTF-8"));

        //response.sendRedirect("/login");
    }

}
