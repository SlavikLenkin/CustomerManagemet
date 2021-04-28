package com.mycompany.kafka.model;

import com.mycompany.model.CustomerDto;
import lombok.Data;

@Data
public class Event {

    private String eventId;
    private String eventTime;
    private String eventType;
    private CustomerDto customerDtoEvent;
}
