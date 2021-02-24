package com.mycompany.repository;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;


@Entity
@Table(name = "customer")
@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
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


    @Column(name = "account_id")
    private String accountId;

    @Column(name = "engaged_party_id")
    private String engagedPartyId;

    @Column(name = "pay_method_id")
    private String payMethodId;

    @Column(name = "contact_medium_id")
    private String contactMediumId;

    @Column(name = "characteristic_id")
    private String characteristicId;

    @Column(name = "agreement_id")
    private String agreementId;

    @Column(name = "related_party_id")
    private String relatedPartyId;


    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_customer")
    private List<CreditProfile> creditProfile ;



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
                "href='" + href + '\''  +
                ", id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", status='" + status + '\'' +
                ", statusReason='" + statusReason + '\'' +
                ", validFor=" + validFor +
                ", accountId='" + accountId + '\'' +
                '}';
    }
}
