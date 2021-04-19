package com.mycompany.repository;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "medium_characteristic")
@Data
public class MediumCharacteristic implements Serializable {

    @Id
    private String id;

    private String city;

    @Column(name = "contact_type")
    private String contactType;

    private String country;

    @Column(name = "email_address")
    private String emailAddress;

    @Column(name = "fax_number")
    private String faxNumber;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "post_code")
    private String postCode;

    @Column(name = "social_network_id")
    private String socialNetworkId;

    @Column(name = "state_or_province")
    private String stateOrProvince;

    @Column(name = "street_1")
    private String street1;

    @Column(name = "street_2")
    private String street2;

    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "contact_medium_id", referencedColumnName = "id")
    private ContactMedium contactMedium;

    @Override
    public String toString() {
        return "MediumCharacteristic{" +
                "id='" + id + '\'' +
                ", city='" + city + '\'' +
                ", contactType='" + contactType + '\'' +
                ", country='" + country + '\'' +
                ", emailAddress='" + emailAddress + '\'' +
                ", faxNumber='" + faxNumber + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", postCode='" + postCode + '\'' +
                ", socialNetworkId='" + socialNetworkId + '\'' +
                ", stateOrProvince='" + stateOrProvince + '\'' +
                ", street1='" + street1 + '\'' +
                ", street2='" + street2 + '\'' +
                '}';
    }
}
