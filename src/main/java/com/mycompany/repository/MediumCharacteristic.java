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

    public MediumCharacteristic() {
    }

    public ContactMedium getContactMedium() {
        return contactMedium;
    }

    public void setContactMedium(ContactMedium contactMedium) {
        this.contactMedium = contactMedium;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getContactType() {
        return contactType;
    }

    public void setContactType(String contactType) {
        this.contactType = contactType;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getFaxNumber() {
        return faxNumber;
    }

    public void setFaxNumber(String faxNumber) {
        this.faxNumber = faxNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public String getSocialNetworkId() {
        return socialNetworkId;
    }

    public void setSocialNetworkId(String socialNetworkId) {
        this.socialNetworkId = socialNetworkId;
    }

    public String getStateOrProvince() {
        return stateOrProvince;
    }

    public void setStateOrProvince(String stateOrProvince) {
        this.stateOrProvince = stateOrProvince;
    }

    public String getStreet1() {
        return street1;
    }

    public void setStreet1(String street1) {
        this.street1 = street1;
    }

    public String getStreet2() {
        return street2;
    }

    public void setStreet2(String street2) {
        this.street2 = street2;
    }

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
