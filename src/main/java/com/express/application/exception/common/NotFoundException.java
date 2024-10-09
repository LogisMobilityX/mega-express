package com.express.application.exception.common;

public class NotFoundException extends RuntimeException{


    private NotFoundException() {
    }

    public NotFoundException(String message) {
        super(message);
    }

    public NotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
