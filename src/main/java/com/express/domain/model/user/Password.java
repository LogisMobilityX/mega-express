package com.express.domain.model.user;

public class Password {
    private final String password;
    private static final String PASSWORD_REGEX = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\\\d)(?=.*[!@#$%^&*(),.?\\\":{}|<>]).{8,}$";
    public Password(String password) {
        if (ValidPasswordPattern(password)){
            throw new RuntimeException();
        }
        this.password = password;
    }

    private boolean ValidPasswordPattern(String password) {
        return password.matches(PASSWORD_REGEX);
    }
    public boolean comparePassword(String inputPassword) {
        if (password.equals(inputPassword)){
           return true;
        }
        return false;
    }

    public String getPasswordText() {
        return password;
    }

    public static Password from(String password){
        return new Password(password);
    }
}
