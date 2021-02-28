package com.mycompany.repository;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.vladmihalcea.hibernate.type.array.StringArrayType;
import lombok.Data;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;


@Entity
@Table(name = "customer")
@Data
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


    @Type(type= "string-array")
    @Column(name = "account_id",columnDefinition = "text[]")
    private String[] accountId;


    @Column(name = "engaged_party_id")
    private String engagedPartyId;

    @Type(type= "string-array")
    @Column(name = "pay_method_id",columnDefinition = "text[]")
    private String[] payMethodId;

    @Type(type= "string-array")
    @Column(name = "contact_medium_id",columnDefinition = "text[]")
    private String[] contactMediumId;

    @Type(type= "string-array")
    @Column(name = "characteristic_id",columnDefinition = "text[]")
    private String[] characteristicId;

    @Type(type= "string-array")
    @Column(name = "agreement_id",columnDefinition = "text[]")
    private String[] agreementId;

    @Type(type= "string-array")
    @Column(name = "related_party_id",columnDefinition = "text[]")
    private String[] relatedPartyId;

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
                ", href='" + href + '\'' +
                ", id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", status='" + status + '\'' +
                ", statusReason='" + statusReason + '\'' +
                ", validFor=" + validFor +
                '}';
    }
}
