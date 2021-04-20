package com.mycompany.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mycompany.repository.Customer;
import lombok.Data;

import java.util.Optional;

@Data
public class AccountDto {

    private Optional<String> description;
    private String href;
    private String id;
    private Optional<String> name;
    @JsonIgnore
    private Customer customer;
}
