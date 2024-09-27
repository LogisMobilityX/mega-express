package com.express.domain.event;

import com.express.domain.model.user.User;
import com.express.infrasturcture.event.EventId;
import org.springframework.context.ApplicationEvent;

import java.time.Clock;

public class UserEvent extends ApplicationEvent {

    private User user;
    public UserEvent(Object source) {
        super(source);
        this.user = (User) source;
    }

    public User getUser() {
        return user;
    }
}
