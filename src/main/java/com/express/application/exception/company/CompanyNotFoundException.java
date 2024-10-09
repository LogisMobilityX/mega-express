package com.express.application.exception.company;


import com.express.application.exception.common.NotFoundException;

public class CompanyNotFoundException extends NotFoundException {


    public CompanyNotFoundException() {
        super("message");
    }

    public CompanyNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
