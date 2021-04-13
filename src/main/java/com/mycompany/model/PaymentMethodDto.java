package com.mycompany.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mycompany.repository.Customer;
import lombok.Data;

@Data
public class PaymentMethodDto {

    private String href;
    private String id;
    private String name;
    @JsonIgnore
    private Customer customer;
}
