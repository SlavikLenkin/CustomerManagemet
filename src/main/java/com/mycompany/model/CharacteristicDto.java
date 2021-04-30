package com.mycompany.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.mycompany.repository.Customer;
import lombok.Data;

import java.util.Optional;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@Data
public class CharacteristicDto {

    private String id;
    private Optional<String> name;
    private Optional<String> value;
    private Optional<String> valueType;
    @JsonIgnore
    private Customer customer;
}
