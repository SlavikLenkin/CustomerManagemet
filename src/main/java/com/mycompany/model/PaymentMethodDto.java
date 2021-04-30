package com.mycompany.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.mycompany.repository.Customer;
import lombok.Data;

import java.util.Optional;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@Data
public class PaymentMethodDto {

    @JsonProperty(value = "@referredType")
    private final String type = "CreditCardPayment";
    private String href;
    private String id;
    private Optional<String> name;
    @JsonIgnore
    private Customer customer;
}
