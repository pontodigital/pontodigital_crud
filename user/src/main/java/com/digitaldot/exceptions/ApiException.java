package com.digitaldot.exceptions;


public class ApiException extends Exception {

    private final int status;
    private final Object responseBody;

    public ApiException(final String message, int status) {
        super(message);
        this.status = status;
        this.responseBody = null;
    }

    public int getStatus() {
        return status;
    }

    public Object getResponseBody() {
        return responseBody;
    }
}
