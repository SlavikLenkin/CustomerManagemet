package com.mycompany.model;

import lombok.Data;

import java.util.List;

@Data
public class Event {

    private String eventId;
    private String eventTime;
    private String eventType;
    private List<CustomerDto> customerDtoEvent;
}
