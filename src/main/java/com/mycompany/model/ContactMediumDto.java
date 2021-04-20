package com.mycompany.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.mycompany.repository.ContactMedium;
import com.mycompany.repository.Customer;
import com.mycompany.repository.ValidFor;
import lombok.Data;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.Optional;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@Data
public class ContactMediumDto {

    private String id;

    private boolean preferred;

    private Optional<String> mediumType;

    private ValidFor validFor;

    @JsonIgnore
    private String MediumCharacteristicId;

    @JsonProperty(value = "characteristic")
    private MediumCharacteristicDto mediumCharacteristic;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    public void setContactMedium(ContactMedium contactMedium) {
        this.id = contactMedium.getId();
        this.preferred = contactMedium.isPreferred();
        this.mediumType = Optional.ofNullable(contactMedium.getMediumType());
        this.validFor = contactMedium.getValidFor();
    }
}
