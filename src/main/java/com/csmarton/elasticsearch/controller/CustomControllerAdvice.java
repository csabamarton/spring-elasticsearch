package com.csmarton.elasticsearch.controller;


import com.csmarton.elasticsearch.exception.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class CustomControllerAdvice {

    public static final String VALIDATION_ERROR_MESSAGE = "Validation errors occurred during this request";

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse>handleUserAlreadyExistsExceptions(MethodArgumentNotValidException  e) {
        Map<String, String> errors = new HashMap<>();
        e.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        HttpStatus status = HttpStatus.BAD_REQUEST; // 400

        return new ResponseEntity<>(new ErrorResponse(status, errors, VALIDATION_ERROR_MESSAGE), status);

    }
}