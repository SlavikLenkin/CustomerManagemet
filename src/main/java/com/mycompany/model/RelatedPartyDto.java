package com.mycompany.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mycompany.repository.Customer;
import lombok.Data;

import java.util.Optional;

@Data
public class RelatedPartyDto {

    private String href;
    private String id;
    private Optional<String> name;
    private Optional<String> role;
    @JsonIgnore
    private Customer customer;
}
