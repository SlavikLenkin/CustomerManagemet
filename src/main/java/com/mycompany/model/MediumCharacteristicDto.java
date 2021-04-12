package com.mycompany.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mycompany.repository.ContactMedium;
import lombok.Data;


@Data
public class MediumCharacteristicDto {

    private String id;
    private String city;
    private String contactType;
    private String country;
    private String emailAddress;
    private String faxNumber;
    private String phoneNumber;
    private String postCode;
    private String socialNetworkId;
    private String stateOrProvince;
    private String street1;
    private String street2;
    @JsonIgnore
    private ContactMedium contactMedium;
}
