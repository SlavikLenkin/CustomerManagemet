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


    @ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinTable(name="customer_account",
            joinColumns = {@JoinColumn(name = "customer_id")},
            inverseJoinColumns ={ @JoinColumn(name = "account_id")})
    private List<Account> accounts = new ArrayList<>();

    public List<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }

    /* @Type(type = "string-array")
    @Column(name = "account_id", columnDefinition = "text[]")
    private String[] accountId;


    @Column(name = "engaged_party_id")
    private String engagedPartyId;

    @Type(type = "string-array")
    @Column(name = "pay_method_id", columnDefinition = "text[]")
    private String[] payMethodId;

    @Type(type = "string-array")
    @Column(name = "contact_medium_id", columnDefinition = "text[]")
    private String[] contactMediumId;

    @Type(type = "string-array")
    @Column(name = "characteristic_id", columnDefinition = "text[]")
    private String[] characteristicId;

    @Type(type = "string-array")
    @Column(name = "agreement_id", columnDefinition = "text[]")
    private String[] agreementId;

    @Type(type = "string-array")
    @Column(name = "related_party_id", columnDefinition = "text[]")
    private String[] relatedPartyId;

    @Type(type = "string-array")
    @Column(name = "credit_profile_id", columnDefinition = "text[]")
    private String[] creditProfileId;*/


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
