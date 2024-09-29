package com.express.application.service.messaging;

import com.express.infrasturcture.messaging.Message;

import java.util.List;

public interface MessagePublisher {

    /* *
     * Publish a list of messages. (메시지 목록을 게시)
     */
    void publish(List<? extends Message> message);
}
