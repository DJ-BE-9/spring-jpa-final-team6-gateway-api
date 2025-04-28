package com.nhnacademy.gateway.service;

import com.nhnacademy.gateway.common.adaptor.login.MemberLoginAdaptor;
import com.nhnacademy.gateway.exception.LoginProcessException;
import com.nhnacademy.gateway.model.domain.LoginProcessMember;
import com.nhnacademy.gateway.model.request.member.MemberLoginRequest;
import com.nhnacademy.gateway.model.dto.ResponseLoginDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Slf4j
@Service
@Transactional
public class LoginMemberDetailsService implements UserDetailsService {

    @Autowired
    private MemberLoginAdaptor loginAdaptor;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        ResponseLoginDto loginResponseDto = loginAdaptor.sendLoginRequest(new MemberLoginRequest(username));
        if(Objects.isNull(loginResponseDto) || Objects.isNull(loginResponseDto.getMemberId()) || loginResponseDto.getMemberId().isEmpty() || Objects.isNull(loginResponseDto.getPassword()) || loginResponseDto.getPassword().isEmpty()) {
            throw new LoginProcessException("로그인 실패");
        }

        return new LoginProcessMember(loginResponseDto.getMemberId(), loginResponseDto.getPassword());
    }


}
