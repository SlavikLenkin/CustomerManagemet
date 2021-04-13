package com.mycompany.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mycompany.repository.Customer;
import lombok.Data;


@Data
public class RelatedPartyDto {

    private String href;
    private String id;
    private String name;
    private String role;
    @JsonIgnore
    private Customer customer;
}
