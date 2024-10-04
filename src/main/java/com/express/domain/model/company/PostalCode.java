package com.express.domain.model.company;


public record PostalCode(int value) {

    public static PostalCode from(final int value) {
        return new PostalCode(value);
    }

}
