package com.express.domain.model.company;

import java.util.regex.Pattern;

public class BusinessNumber {

    private final String number;
    private static final String BUSINESS_NUMBER_REGEX = "^\\d{10}$"; //사업자 번호 숫자 10자리


    private static final Pattern BUSINESS_NUMBER_PATTERN = Pattern.compile(BUSINESS_NUMBER_REGEX);

    private BusinessNumber(String value) {
        if(!isValidEmailAddress(value)) throw new RuntimeException();if(!isValidEmailAddress(value)) throw new RuntimeException();
        this.number = value;
    }

    private boolean isValidEmailAddress(final String value) {
        return BUSINESS_NUMBER_PATTERN.matcher(value).matches();
    }

    public static BusinessNumber from(final String value) {
        return new BusinessNumber(value);
    }

}
