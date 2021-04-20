package com.mycompany.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mycompany.repository.ContactMedium;
import lombok.Data;

import java.util.Optional;

@Data
public class MediumCharacteristicDto {

    private String id;
    private Optional<String> city;
    private Optional<String> contactType;
    private Optional<String> country;
    private Optional<String> emailAddress;
    private Optional<String> faxNumber;
    private Optional<String> phoneNumber;
    private Optional<String> postCode;
    private Optional<String> socialNetworkId;
    private Optional<String> stateOrProvince;
    private Optional<String> street1;
    private Optional<String> street2;
    @JsonIgnore
    private ContactMedium contactMedium;
}
