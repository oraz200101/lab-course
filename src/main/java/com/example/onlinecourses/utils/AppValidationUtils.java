package com.example.onlinecourses.utils;


import com.example.onlinecourses.exception.domain.CustomValidationException;
import com.example.onlinecourses.models.exception.ApiValidationError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.validation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@Service
public class AppValidationUtils extends ValidationUtils {

    @Autowired
    private MessageSource messageSource;

    public void invokeValidator(Validator validator, @Nullable Object obj, Class objectType, @Nullable Object... validationHints) {
        Errors errors = new BeanPropertyBindingResult(obj, objectType.getName());
        invokeValidator(validator, obj, errors, validationHints);

        if (errors.hasErrors()) {
            List<ApiValidationError> validationErrors = getValidationErrors(errors);
            String errorMessage = validationErrors.get(0).getMessage();
            throw new CustomValidationException(errorMessage, validationErrors);
        }
    }

    //Todo translate message here. Get Locale and translate.
    public String getErrors(Errors errors) {
        List<ObjectError> objectErrors = errors.getAllErrors();
        String response = "";
        int size = objectErrors.size();
        for (int i = 0; i < size; i++) {
            ObjectError error = objectErrors.get(i);
            if (i != 0 || i != size - 1) {
                response.concat(" , ");
            }
            response = response.concat(Objects.requireNonNull(error.getDefaultMessage()));
        }

        return response;
    }

    /**
     * Gets field errors.
     *
     * @param errors
     * @return
     */
    public List<ApiValidationError> getValidationErrors(Errors errors) {
        List<ApiValidationError> validationErrors = new ArrayList<>();
        ApiValidationError validationError;
        List<ObjectError> objectErrors = errors.getAllErrors();
        int size = objectErrors.size();
        for (int i = 0; i < size; i++) {
            ObjectError error = objectErrors.get(i);

            validationError = new ApiValidationError();

            if (error instanceof FieldError) {
                validationError.setObject(error.getObjectName());
                FieldError fieldError = (FieldError) error;
                validationError.setField(fieldError.getField());
                validationError.setRejectedValue(fieldError.getRejectedValue());
            }

            validationError.setMessage(error.getDefaultMessage());

            validationErrors.add(validationError);
        }

        return validationErrors;
    }

}
