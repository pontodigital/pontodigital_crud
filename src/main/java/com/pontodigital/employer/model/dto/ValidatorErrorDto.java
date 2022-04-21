package com.pontodigital.employer.model.dto;

import java.util.List;

public class ValidatorErrorDto {
    private Object message;
    private long timestamp;

    public ValidatorErrorDto(Object message) {
        this.message = message;
        this.timestamp = System.currentTimeMillis();
    }

    public Object getMessage() {
        return message;
    }

    public void setMessage(List<String> message) {
        this.message = message;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
}
