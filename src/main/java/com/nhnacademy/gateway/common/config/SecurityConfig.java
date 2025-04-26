package com.nhnacademy.gateway.common.config;

import com.nhnacademy.gateway.common.filter.RedisSessionFilter;
import com.nhnacademy.gateway.handler.CustomAuthenticationFailureHandler;
import com.nhnacademy.gateway.handler.CustomAuthenticationSuccessHandler;
import com.nhnacademy.gateway.handler.CustomLogoutHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, RedisSessionFilter redisSessionFilter) throws Exception {

        CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler = new CustomAuthenticationSuccessHandler(redisTemplate);
        CustomAuthenticationFailureHandler customAuthenticationFailureHandler = new CustomAuthenticationFailureHandler();
        CustomLogoutHandler customLogoutHandler = new CustomLogoutHandler(redisTemplate);

        http
                .csrf(AbstractHttpConfigurer::disable)

                .authorizeHttpRequests(auth -> auth // 권한에 따른 접근 가능 페이지
//                        .requestMatchers("/task/**").authenticated()
//                                .requestMatchers("/").permitAll()
//                                .requestMatchers("/register").permitAll()
                        .anyRequest().permitAll()
                )

                .formLogin(form -> form // 로그인
                        .loginPage("/login")
                        .loginProcessingUrl("/login")
                        .usernameParameter("memberId")
                        .passwordParameter("password")
                        .successHandler(customAuthenticationSuccessHandler)
                        .failureHandler(customAuthenticationFailureHandler)
                        .permitAll()
                )

                .logout(logout -> logout
                        .addLogoutHandler(customLogoutHandler)
                        .logoutUrl("/logout"))

                .addFilterBefore(redisSessionFilter, UsernamePasswordAuthenticationFilter.class);


        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
