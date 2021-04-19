package com.mycompany.repository;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "contact_medium")
@Data
public class ContactMedium {

    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "preferred")
    private boolean preferred;

    @Column(name = "medium_type")
    private String mediumType;

    @Embedded
    private ValidFor validFor;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @OneToOne(mappedBy = "contactMedium")
    private MediumCharacteristic mediumCharacteristic;

    @Override
    public String toString() {
        return "ContactMedium{" +
                ", preferred=" + preferred +
                ", mediumType='" + mediumType + '\'' +
                ", validFor=" + validFor +
                '}';
    }
}
