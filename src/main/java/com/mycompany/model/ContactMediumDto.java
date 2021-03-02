package com.mycompany.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.mycompany.repository.ContactMedium;
import com.mycompany.repository.MediumCharacteristic;
import com.mycompany.repository.ValidFor;

//@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ContactMediumDto {


    private String id;


    private boolean preferred;


    private String mediumType;


    private ValidFor validFor;

    @JsonIgnore
    private String MediumCharacteristicId;

    @JsonProperty(value = "characteristic")
    private MediumCharacteristic mediumCharacteristic;

    public void setContactMedium(ContactMedium contactMedium) {
        this.id = contactMedium.getId();
        this.preferred = contactMedium.isPreferred();
        this.mediumType = contactMedium.getMediumType();
        this.validFor = contactMedium.getValidFor();
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isPreferred() {
        return preferred;
    }

    public void setPreferred(boolean preferred) {
        this.preferred = preferred;
    }

    public String getMediumType() {
        return mediumType;
    }

    public void setMediumType(String mediumType) {
        this.mediumType = mediumType;
    }

    public ValidFor getValidFor() {
        return validFor;
    }

    public void setValidFor(ValidFor validFor) {
        this.validFor = validFor;
    }

    public String getMediumCharacteristicId() {
        return MediumCharacteristicId;
    }

    public void setMediumCharacteristicId(String mediumCharacteristicId) {
        MediumCharacteristicId = mediumCharacteristicId;
    }

    public MediumCharacteristic getMediumCharacteristic() {
        return mediumCharacteristic;
    }

    public void setMediumCharacteristic(MediumCharacteristic mediumCharacteristic) {
        this.mediumCharacteristic = mediumCharacteristic;
    }
}
