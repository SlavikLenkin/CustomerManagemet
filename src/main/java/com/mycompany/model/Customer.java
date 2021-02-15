package com.mycompany.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;


@Entity
@Table(name = "customer")
@Data
//@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Customer implements Serializable {

    @Column(name = "href")
    private String href;

    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "name")
    private String name;

    @Column(name = "status")
    private String status;

    @Column(name = "status_reason")
    private String statusReason;

    @Embedded
    private ValidForCustomer validFor;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_customer")
    private List<EngagedParty> engagedParty;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_customer")
    private List<Account> accounts;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_customer")
    private List<PaymentMethod> paymentMethods ;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_customer")
    private List<ContactMedium> contactMedium ;



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

    public ValidForCustomer getValidFor() {
        return validFor;
    }

    public void setValidFor(ValidForCustomer validFor) {
        this.validFor = validFor;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }

}
