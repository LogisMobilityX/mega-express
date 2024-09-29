package com.express.domain.model;

import com.express.infrasturcture.event.DomainEvent;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
public class InternalDomainEvent implements DomainEvent {


    private UUID eventId;
    private LocalDateTime occurrenceTime;


    public InternalDomainEvent() {
        this.eventId = UUID.randomUUID();
        this.occurrenceTime = LocalDateTime.now();
    }
}
