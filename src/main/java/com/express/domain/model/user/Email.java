package com.express.domain.model.user;

public class Email {
    private static final String EMAIL_REGEX = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
    private final String email;

    public Email(String email) {
        if (!ValidEmailPattern(email)){
            throw new RuntimeException();
        }
        this.email = email;
    }

    public boolean ValidEmailPattern(String email){
        return email.matches(EMAIL_REGEX);
    }

    public String getEmailText() {
        return email;
    }

    public static Email from(String email){
        return new Email(email);
    }

}
