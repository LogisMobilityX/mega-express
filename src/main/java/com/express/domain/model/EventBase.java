package com.express.domain.model;

import com.express.infrasturcture.aggregate.AggregateRoot;
import com.express.infrasturcture.event.DomainEvent;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;



@Getter
@AllArgsConstructor
@NoArgsConstructor
public class EventBase implements AggregateRoot {
    private List<DomainEvent> domainEventList;

    @Override
    public void clearEvents() {
        domainEventList.clear();
    }

    @Override
    public List<DomainEvent> listEvents() {
        return List.copyOf(domainEventList);
    }
}
