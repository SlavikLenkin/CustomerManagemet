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

    @JsonProperty(value = "@referredType")
    private final String type = "TelephoneMedium";
    @JsonProperty(value = "@schemaLocation")
    private final String location = "https://my.schemas/TelephoneMedium.schema.json";
    private String id;
    private Optional<Boolean> preferred;
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
        this.preferred = Optional.ofNullable(contactMedium.getPreferred());
        this.mediumType = Optional.ofNullable(contactMedium.getMediumType());
        this.validFor = contactMedium.getValidFor();
    }
}
