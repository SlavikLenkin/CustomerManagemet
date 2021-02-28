package com.mycompany.repository;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "contact_medium")
public class ContactMedium {

    @JsonIgnore
    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "preferred")
    private boolean preferred;

    @Column(name = "medium_type")
    private String mediumType;

    @Embedded
    private ValidFor validFor;


    public ContactMedium() {
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

    @Override
    public String toString() {
        return "ContactMedium{" +
                "id='" + id + '\'' +
                ", preferred=" + preferred +
                ", mediumType='" + mediumType + '\'' +
                ", validFor=" + validFor +
                '}';
    }
}
