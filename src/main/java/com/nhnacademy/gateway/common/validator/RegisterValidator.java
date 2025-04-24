package com.nhnacademy.gateway.common.validator;

import com.nhnacademy.gateway.model.dto.RegisterRequest;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class RegisterValidator implements Validator{

    @Override
    public boolean supports(Class<?> clazz) {
        return RegisterRequest.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "id", "", "id is empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "", "password is empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "", "email is empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "", "name is empty");
    }

}
