package com.mycompany.repository;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.vladmihalcea.hibernate.type.array.StringArrayType;
import lombok.Data;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Entity
@Table(name = "customer")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@TypeDef(
        name = "string-array",
        typeClass = StringArrayType.class
)
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

    public List<ContactMedium> getContactMediumList() {
        return contactMediumList;
    }

    public void setContactMediumList(List<ContactMedium> contactMediumList) {
        this.contactMediumList = contactMediumList;
    }

    public EngagedParty getEngagedParty() {
        return engagedParty;
    }

    public void setEngagedParty(EngagedParty engagedParty) {
        this.engagedParty = engagedParty;
    }

    public List<RelatedParty> getRelatedParties() {
        return relatedParties;
    }

    public void setRelatedParties(List<RelatedParty> relatedParties) {
        this.relatedParties = relatedParties;
    }

    public List<PaymentMethod> getPaymentMethods() {
        return paymentMethods;
    }

    public void setPaymentMethods(List<PaymentMethod> paymentMethods) {
        this.paymentMethods = paymentMethods;
    }

    public List<CreditProfile> getCreditProfiles() {
        return creditProfiles;
    }

    public void setCreditProfiles(List<CreditProfile> creditProfiles) {
        this.creditProfiles = creditProfiles;
    }

    public List<Characteristic> getCharacteristics() {
        return characteristics;
    }

    public void setCharacteristics(List<Characteristic> characteristics) {
        this.characteristics = characteristics;
    }

    public List<Agreement> getAgreements() {
        return agreements;
    }

    public void setAgreements(List<Agreement> agreements) {
        this.agreements = agreements;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }


    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatusReason() {
        return statusReason;
    }

    public void setStatusReason(String statusReason) {
        this.statusReason = statusReason;
    }

    public ValidFor getValidFor() {
        return validFor;
    }

    public void setValidFor(ValidFor validFor) {
        this.validFor = validFor;
    }


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
