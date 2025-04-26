package com.nhnacademy.gateway.controller.login;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class MemberIndexController {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @GetMapping
    public String index(HttpServletRequest request,
                        HttpServletResponse response) {

        Cookie[] cookies = request.getCookies();
        if(cookies != null) {
            for(Cookie cookie : cookies) {
                if(cookie.getName().equals("SESSIONID")) {
                    String sessionId = cookie.getValue();
                    redisTemplate.delete(sessionId);

                    cookie.setValue("");
                    cookie.setMaxAge(0);
                    cookie.setPath("/");
                    response.addCookie(cookie);
                    break;
                }
            }
        }

        return "index";
    }

}
