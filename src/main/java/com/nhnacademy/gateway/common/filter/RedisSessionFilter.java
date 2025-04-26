package com.nhnacademy.gateway.common.filter;

import com.nhnacademy.gateway.common.adaptor.login.MemberLoginAdaptor;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;
import java.util.Objects;

/**
 * RedisSessionFilter는 Spring Security의 인증 흐름에 끼어들어서 쿠키에 저장된 세션 ID를 기반으로 Redis에서 사용자 정보를 찾아 인증을 시켜주는 커스텀 필터이다.
 */
@Component
public class RedisSessionFilter extends OncePerRequestFilter {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Autowired
    private MemberLoginAdaptor loginAdaptor;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        String sessionId = null;
        Cookie[] cookies = request.getCookies();
        if(Objects.nonNull(cookies)) {
            for(Cookie cookie : cookies) {
                if(cookie.getName().equals("SESSIONID")) {
                    sessionId = cookie.getValue();
                    break;
                }
            }
        }

        if(sessionId != null) {
            String memberId = redisTemplate.opsForValue().get(sessionId);

            if(Objects.nonNull(memberId)) {
                UserDetails userDetails = User.withUsername(memberId)
                        .password("")
                        .authorities(Collections.emptyList())
                        .build();

                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);

            }
        }

        filterChain.doFilter(request, response);
    }
}
