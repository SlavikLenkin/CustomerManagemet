package com.mycompany.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.mycompany.repository.Customer;
import lombok.Data;

import javax.persistence.Transient;
import java.util.Optional;

@Data
public class EngagedPartyDto {
    @JsonProperty(value = "@referredType")
    @Transient
    private final String type = "Organization";
    private String href;
    private String id;
    private Optional<String> name;
    @JsonIgnore
    private Customer customer;
}
