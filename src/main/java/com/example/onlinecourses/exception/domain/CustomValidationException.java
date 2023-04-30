package com.example.onlinecourses.exception.domain;

import com.example.onlinecourses.models.exception.ApiValidationError;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.ArrayList;
import java.util.List;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class CustomValidationException extends RuntimeException {
    private List<ApiValidationError> subErrors;

    public CustomValidationException(String msg) {
        super(msg);
    }

    public CustomValidationException(String msg, List<ApiValidationError> validationErrors) {
        super(msg);
        this.subErrors = new ArrayList<>(validationErrors);
    }

    public List<ApiValidationError> getValidationErrorList() {
        return subErrors;
    }

}

