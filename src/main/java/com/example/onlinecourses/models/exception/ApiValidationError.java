package com.example.onlinecourses.models.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiValidationError {
    private String object;
  
    private String field;

    private Object rejectedValue;

    private String message;
}
