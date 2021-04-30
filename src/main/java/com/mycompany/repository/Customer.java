package com.mycompany.repository;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "customer")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@Data
public class Customer implements Serializable {

    @Column(name = "href")
    private String href;

    @Id
    private String id;

    @Column(name = "name")
    private String name;

    @Column(name = "status")
    private String status;

    @Column(name = "status_reason")
    private String statusReason;

    @Embedded
    private ValidFor validFor;

    @OneToOne(mappedBy = "customer")
    private EngagedParty engagedParty;

    @OneToMany(mappedBy = "customer")
    private List<Account> accounts;

    @OneToMany(mappedBy = "customer")
    private List<Agreement> agreements;

    @OneToMany(mappedBy = "customer")
    private List<Characteristic> characteristics;

    @OneToMany(mappedBy = "customer")
    private List<CreditProfile> creditProfiles;

    @OneToMany(mappedBy = "customer")
    private List<PaymentMethod> paymentMethods;

    @OneToMany(mappedBy = "customer")
    private List<RelatedParty> relatedParties;

    @OneToMany(mappedBy = "customer")
    private List<ContactMedium> contactMediumList;

    @Override
    public String toString() {
        return "Customer{" +
                ", href='" + href + '\'' +
                ", id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", status='" + status + '\'' +
                ", statusReason='" + statusReason + '\'' +
                ", validFor=" + validFor +
                '}';
    }
}
