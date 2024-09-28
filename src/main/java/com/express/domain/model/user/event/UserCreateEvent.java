package com.express.domain.model.user.event;

import com.express.domain.model.user.User;
import org.springframework.context.ApplicationEvent;

public class UserCreateEvent extends ApplicationEvent {

    private User user;
    public UserCreateEvent(Object source) {
        super(source);
        this.user = (User) source;
    }

    public User getUser() {
        return user;
    }
}
