package com.express.infrasturcture.aggregate;


import com.express.infrasturcture.event.DomainEvent;

import java.util.List;

/**
 * Base interface for aggregate roots.
 *
 * @author Mate Karolyi
 */
public interface AggregateRoot {
    /**
     * Method for clearing domain events.
     */
    void clearEvents();

    /**
     * Method for listing domain events.
     *
     * @return a list of domain events
     */
    List<DomainEvent> listEvents();
}
