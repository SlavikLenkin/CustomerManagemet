package com.mycompany.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.mycompany.repository.Customer;
import lombok.Data;

import java.util.Optional;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@Data
public class RelatedPartyDto {

    @JsonProperty(value = "@referredType")
    private final String type = "Organization";
    private String href;
    private String id;
    private Optional<String> name;
    private Optional<String> role;
    @JsonIgnore
    private Customer customer;
}
