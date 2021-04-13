package com.mycompany.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mycompany.repository.Customer;
import com.mycompany.repository.ValidFor;
import lombok.Data;

import javax.persistence.Embedded;
import java.util.Date;

@Data
public class CreditProfileDto {

    private String id;
    private Date creditProfileDate;
    private int creditRiskRating;
    private int creditScore;
    @Embedded
    private ValidFor validFor;
    @JsonIgnore
    private Customer customer;
}