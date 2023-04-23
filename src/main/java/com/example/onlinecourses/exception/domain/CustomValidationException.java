package com.example.onlinecourses.exception.domain;

import com.example.onlinecourses.exception.CustomException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class CustomValidationException extends CustomException {

    public CustomValidationException(String message, String code) {
        super(message, code, HttpStatus.BAD_REQUEST);
    }
}
