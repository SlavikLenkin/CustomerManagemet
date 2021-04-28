package com.mycompany.kafka.model;

import lombok.Data;

@Data
public class CustomerStateChangeEvent {

    private String eventId;
    private String eventTime;
    private String eventType;
    private String status;
    private String newStatus;
}
