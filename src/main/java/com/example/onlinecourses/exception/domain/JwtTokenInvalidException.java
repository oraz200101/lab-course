package com.example.onlinecourses.exception.domain;

import com.example.onlinecourses.exception.CustomException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import static com.example.onlinecourses.constants.jwt.JwtConstants.JWT_DOESNT_VALID_CODE;
import static com.example.onlinecourses.constants.jwt.JwtConstants.JWT_DOESNT_VALID_MESSAGE;
@ResponseStatus(code = HttpStatus.UNAUTHORIZED, reason = JWT_DOESNT_VALID_MESSAGE)
public class JwtTokenInvalidException extends CustomException {
    public JwtTokenInvalidException() {
        super(JWT_DOESNT_VALID_MESSAGE, JWT_DOESNT_VALID_CODE, HttpStatus.UNAUTHORIZED);
    }
}
