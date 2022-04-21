package com.digitaldot.employer.exceptions;

import com.digitaldot.employer.model.dto.EmployerErrorDto;

public class ApiException extends Exception {

    private final int status;
    private final Object responseBody;

    public ApiException(final String message, int status) {
        super(message);
        this.status = status;
        this.responseBody = new EmployerErrorDto(message);
    }

    public int getStatus() {
        return status;
    }

    public Object getResponseBody() {
        return responseBody;
    }
}
