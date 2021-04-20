package com.mycompany.repository;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "engaged_party")
@Data
public class EngagedParty implements Serializable {

    @JsonProperty(value = "@referredType")
    @Transient
    private final String type = "Organization";

    private String href;

    @Id
    private String id;

    private String name;

    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    private Customer customer;

    @Override
    public String toString() {
        return "EngagedParty{" +
                "type='" + type + '\'' +
                ", href='" + href + '\'' +
                ", id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
