package com.express.application.port.output.messaging;

import com.express.infrasturcture.messaging.Message;
import java.util.List;


public interface MessagePublisher {

    void publish(List<? extends Message> messages);

}
