package com.example.onlinecourses.exception.domain;

import com.example.onlinecourses.exception.CustomException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import static com.example.onlinecourses.constants.exception.UserExceptionConstants.USER_NOT_FOUND_BY_EMAIL_CODE;
import static com.example.onlinecourses.constants.exception.UserExceptionConstants.USER_NOT_FOUND_BY_EMAIL_MESSAGE;

@ResponseStatus(value = HttpStatus.NOT_FOUND,reason = USER_NOT_FOUND_BY_EMAIL_MESSAGE )
public class UserNotFoundByEmailException extends CustomException {
    public UserNotFoundByEmailException() {
        super(USER_NOT_FOUND_BY_EMAIL_MESSAGE, USER_NOT_FOUND_BY_EMAIL_CODE, HttpStatus.NOT_FOUND);
    }
}
