package com.digitaldot.validator;

import com.digitaldot.validator.dto.ErrorDto;
import com.digitaldot.validator.dto.ValidatorErrorDto;
import com.digitaldot.validator.exceptions.ApiException;
import com.digitaldot.validator.exceptions.ValidatorErrorException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionController {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDto> handlerApiException(IllegalArgumentException e) {
        return new ResponseEntity<>(new ErrorDto(e.getMessage()), HttpStatus.BAD_GATEWAY);
    }

    @ExceptionHandler(ApiException.class)
    public ResponseEntity<ErrorDto> handlerApiException(ApiException e) {
        return new ResponseEntity<>(new ErrorDto(e.getMessage()), HttpStatus.valueOf(e.getStatus()));
    }

    @ExceptionHandler(ValidatorErrorException.class)
    public ResponseEntity<ValidatorErrorDto> handlerBindingResultException(ValidatorErrorException e) {
        return new ResponseEntity<>(new ValidatorErrorDto(e.getResponseBody()), HttpStatus.valueOf(e.getStatus()));
    }
}
