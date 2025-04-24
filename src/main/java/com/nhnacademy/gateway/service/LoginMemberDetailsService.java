package com.nhnacademy.gateway.service;

import com.nhnacademy.gateway.common.adaptor.login.LoginAdaptor;
import com.nhnacademy.gateway.exception.LoginProcessException;
import com.nhnacademy.gateway.model.domain.LoginProcessMember;
import com.nhnacademy.gateway.model.dto.LoginRequest;
import com.nhnacademy.gateway.model.dto.LoginResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Slf4j
@Service
public class LoginMemberDetailsService implements UserDetailsService {

    @Autowired
    private LoginAdaptor loginAdaptor;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        LoginResponseDto loginResponseDto = loginAdaptor.sendLoginRequest(new LoginRequest(username));
        if(Objects.isNull(loginResponseDto) || Objects.isNull(loginResponseDto.getMemberId()) || loginResponseDto.getMemberId().isEmpty() || Objects.isNull(loginResponseDto.getPassword()) || loginResponseDto.getPassword().isEmpty()) {
            throw new LoginProcessException("로그인 실패");
        }

        log.info("id:{}", loginResponseDto.getMemberId());
        log.info("password:{}", loginResponseDto.getPassword());

        return new LoginProcessMember(loginResponseDto.getMemberId(), loginResponseDto.getPassword());
    }


}
