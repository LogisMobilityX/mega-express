package com.express.domain.model.user.event.publisher;


import com.express.application.service.messaging.MessagePublisher;
import com.express.infrasturcture.messaging.Message;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class UserCreateEventPublisher implements MessagePublisher {
    private final ApplicationEventPublisher applicationEventPublisher;
    @Override
    public void publish(List<? extends Message> message) {

    }
}
