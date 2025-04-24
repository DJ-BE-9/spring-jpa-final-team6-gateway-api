package com.nhnacademy.gateway.controller;

import com.nhnacademy.gateway.common.adaptor.RegisterAdaptor;
import com.nhnacademy.gateway.exception.RegisterProcessException;
import com.nhnacademy.gateway.exception.ValidationFailedException;
import com.nhnacademy.gateway.model.dto.RegisterRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/register")
public class RegisterController {

    @Autowired
    private RegisterAdaptor registerAdaptor;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping
    public String getRegister() {
        return "registerForm";
    }

    @PostMapping
    public String postRegister(@Validated @ModelAttribute RegisterRequest registerRequest,
                               BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            throw new ValidationFailedException(bindingResult);
        }

        String memberId = registerRequest.getMemberId();
        String encodedPassword = passwordEncoder.encode(registerRequest.getPassword());
        registerRequest.setPassword(encodedPassword);

        if(!registerAdaptor.sendRegisterRequest(registerRequest)) {
            throw new RegisterProcessException("회원가입 실패했습니다.");
        }


        return "redirect:/login";
    }

}
