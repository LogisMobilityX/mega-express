package com.express.adapter.output.publisher;


import com.express.application.port.output.messaging.MessagePublisher;
import com.express.infrasturcture.messaging.Message;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class SpringEventPublisher implements MessagePublisher {
    private final ApplicationEventPublisher applicationEventPublisher;
    @Override
    public void publish(List<? extends Message> message) {
        message.forEach(applicationEventPublisher::publishEvent);
    }
}
