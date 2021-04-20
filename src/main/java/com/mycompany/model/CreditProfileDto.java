package com.mycompany.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mycompany.repository.Customer;
import com.mycompany.repository.ValidFor;
import lombok.Data;

import javax.persistence.Embedded;
import java.util.Date;
import java.util.Optional;

@Data
public class CreditProfileDto {

    private String id;
    private Optional<Date> creditProfileDate;
    private Optional<Integer> creditRiskRating;
    private Optional<Integer> creditScore;
    @Embedded
    private ValidFor validFor;
    @JsonIgnore
    private Customer customer;
}
