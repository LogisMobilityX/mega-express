package com.express.domain.model.user;

public class UserName {

    private final String userName;

    public UserName(String userName) {
        if (userName == null) {throw new RuntimeException();}
        this.userName = userName;
    }

    public String getUserNameText() {
        return userName;
    }

    public static UserName from(String userName){
        return new UserName(userName);
    }
}
