package com.express.domain.model.user;

public class PhoneNumber {
    private final String phoneNumber;
    private static final String PHONE_NUMBER_REGEX = "^(\\+82-?)?(01[016789]-\\d{3,4}-\\d{4}|02-\\d{3,4}-\\d{4}|0\\d{2}-\\d{3,4}-\\d{4})$";

    public PhoneNumber(String phoneNumber) {
        if (ValidPhoneNumberPattern(phoneNumber)){
            throw new RuntimeException();
        }
        this.phoneNumber = phoneNumber;
    }

    private boolean ValidPhoneNumberPattern(String phoneNumber) {
        return phoneNumber.matches(PHONE_NUMBER_REGEX);
    }

    public String getPhoneNumberText() {
        return phoneNumber;
    }

    public static PhoneNumber from(String phoneNumber){
        return new PhoneNumber(phoneNumber);
    }
}
