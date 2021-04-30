package com.mycompany.kafka.service;

import com.mycompany.kafka.model.CustomerStateChangeEvent;
import com.mycompany.kafka.model.Event;
import com.mycompany.model.CustomerDto;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class EventService {

    private int eventId = 0;

    public Event createEvent(CustomerDto customerDto, String eventType) {
        Event event = new Event();
        eventId++;
        event.setEventId(String.valueOf(eventId));
        event.setEventType(eventType);
        event.setEventTime(new Date().toString());
        event.setCustomerDtoEvent(customerDto);
        return event;
    }

    public CustomerStateChangeEvent createEvent(Optional status, Optional newStatus, String eventType) {
        CustomerStateChangeEvent stateChangeEvent = new CustomerStateChangeEvent();
        eventId++;
        stateChangeEvent.setEventId(String.valueOf(eventId));
        if (status == null) {
            stateChangeEvent.setStatus(null);
        } else
            stateChangeEvent.setStatus(Optional.ofNullable(status).toString());
        if (newStatus == null) {
            stateChangeEvent.setNewStatus(null);
        } else
            stateChangeEvent.setNewStatus(Optional.ofNullable(newStatus).toString());
        stateChangeEvent.setEventType(eventType);
        stateChangeEvent.setEventTime(new Date().toString());
        return stateChangeEvent;
    }
}
