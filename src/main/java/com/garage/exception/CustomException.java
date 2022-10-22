package com.garage.exception;

import org.springframework.http.HttpStatus;

public class CustomException extends RuntimeException {

    private String message;
    private HttpStatus httpStatus;
    private final Object[] parameters;

    public CustomException(String message, HttpStatus httpStatus, Object... parameters) {
        super(message);
        this.message = message;
        this.httpStatus = httpStatus;
        this.parameters = parameters;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public Object[] getParameters() {
        return parameters;
    }
}