package com.digitaldot.validator.exceptions;

import com.digitaldot.validator.dto.ErrorDto;

public class ApiException extends Exception {

    private final int status;
    private final Object responseBody;

    public ApiException(final String message, int status) {
        super(message);
        this.status = status;
        this.responseBody = new ErrorDto(message);
    }

    public int getStatus() {
        return status;
    }

    public Object getResponseBody() {
        return responseBody;
    }
}
