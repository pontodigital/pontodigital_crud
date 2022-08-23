package com.digitaldot.controller;

import com.digitaldot.model.dto.EmployerErrorDto;
import com.digitaldot.model.dto.ValidatorErrorDto;
import com.digitaldot.exceptions.ApiException;
import com.digitaldot.exceptions.ValidatorErrorException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionController {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<EmployerErrorDto> handlerApiException(IllegalArgumentException e) {
        return new ResponseEntity<>(new EmployerErrorDto(e.getMessage()), HttpStatus.BAD_GATEWAY);
    }

    @ExceptionHandler(ApiException.class)
    public ResponseEntity<EmployerErrorDto> handlerApiException(ApiException e) {
        return new ResponseEntity<>(new EmployerErrorDto(e.getMessage()), HttpStatus.valueOf(e.getStatus()));
    }

    @ExceptionHandler(ValidatorErrorException.class)
    public ResponseEntity<ValidatorErrorDto> handlerBindingResultException(ValidatorErrorException e) {
        return new ResponseEntity<>(new ValidatorErrorDto(e.getResponseBody()), HttpStatus.valueOf(e.getStatus()));
    }
}
