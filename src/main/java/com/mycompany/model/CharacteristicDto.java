package com.mycompany.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mycompany.repository.Customer;
import lombok.Data;

@Data
public class CharacteristicDto {

    private String id;
    private String name;
    private String value;
    private String valueType;
    @JsonIgnore
    private Customer customer;
}
