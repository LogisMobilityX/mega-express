package com.express.domain.model.user;

public class UserId {
    private final Long userId;

    public UserId(Long userId) {
        this.userId = userId;
    }


    public Long Id() {
        return userId;
    }

    public static UserId from(Long userId){
        return new UserId(userId);
    }
}
