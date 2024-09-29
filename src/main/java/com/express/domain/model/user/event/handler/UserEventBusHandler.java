package com.express.domain.model.user.event.handler;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
@Slf4j
@RequiredArgsConstructor
public class UserEventBusHandler {
    /**
     * 유저 관련 이벤트를 처리
     *
     */

    //save User 이후 처리 될 이벤트
    @Async
    @TransactionalEventListener
    public void Handle(){

    }

}
