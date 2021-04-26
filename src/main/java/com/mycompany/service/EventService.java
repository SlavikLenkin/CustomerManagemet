package com.mycompany.service;

import com.mycompany.model.CustomerDto;
import com.mycompany.model.Event;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class EventService {

    private int eventId = 0;

    private Event createEvent(String eventType) {
        Event event = new Event();
        eventId++;
        event.setEventId("" + eventId);
        event.setEventType(eventType);
        event.setEventTime(new Date().toString());
        return event;
    }

    public Event createEvent(CustomerDto customerDto, String eventType) {
        Event event = createEvent(eventType);
        List<CustomerDto> customerDtoList = new ArrayList<>();
        customerDtoList.add(customerDto);
        event.setCustomerDtoEvent(customerDtoList);
        return event;
    }

    public Event createEvent(Optional status, String eventType) {
        Event event = createEvent(eventType);
        List<CustomerDto> customerDtoList = new ArrayList<>();
        CustomerDto customerDto = new CustomerDto();
        customerDto.setStatus(status);
        customerDtoList.add(customerDto);
        event.setCustomerDtoEvent(customerDtoList);
        return event;
    }
}
