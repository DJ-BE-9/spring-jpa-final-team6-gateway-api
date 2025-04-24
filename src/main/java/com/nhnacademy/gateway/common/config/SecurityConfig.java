package com.nhnacademy.gateway.common.config;

import com.nhnacademy.gateway.handler.CustomAuthenticationFailureHandler;
import com.nhnacademy.gateway.handler.CustomAuthenticationSuccessHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        CustomAuthenticationSuccessHandler successHandler = new CustomAuthenticationSuccessHandler();
        CustomAuthenticationFailureHandler failureHandler = new CustomAuthenticationFailureHandler();

        http
                .csrf(AbstractHttpConfigurer::disable)

                .authorizeHttpRequests(auth -> auth // 권한에 따른 접근 가능 페이지
                        .anyRequest().permitAll()
                )

                .formLogin(form -> form // 로그인
                        .loginPage("/loginForm")
                        .usernameParameter("id")
                        .passwordParameter("pwd")
                        .loginProcessingUrl("/login")
                        .successHandler(successHandler)
                        .failureHandler(failureHandler)
                        .permitAll()
                );



        http.formLogin(Customizer.withDefaults());

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
