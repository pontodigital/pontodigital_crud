package com.digitaldot.employer.exceptions;

import java.util.List;

public class ValidatorErrorException extends Exception {
    private final int status;
    private final Object responseBody;

    public ValidatorErrorException(final List<String> message, int status) {
        super(message.toString());
        this.status = status;
        this.responseBody = message;
    }

    public int getStatus() {
        return status;
    }

    public Object getResponseBody() {
        return responseBody;
    }
}
