package com.mycompany.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.mycompany.repository.Account;
import com.mycompany.repository.Customer;
import com.mycompany.repository.EngagedParty;
import com.mycompany.repository.ValidFor;

import java.util.List;


@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class FullCustomer {



    private String href;

    private String id;

    private String name;

    private String status;

    private String statusReason;

    private ValidFor validFor;

    private EngagedParty engagedParty;

    private List<Account> accounts;




    @JsonIgnore
    public Customer getCustomer(){
        Customer customer = new Customer();
        customer.setHref(href);
        customer.setId(id);
        customer.setName(name);
        customer.setStatus(status);
        customer.setStatusReason(statusReason);
        customer.setValidFor(validFor);

        return customer;
    }

    public void setCustomer(Customer customer) {
        this.href = customer.getHref();
        this.id = customer.getId();
        this.name = customer.getName();
        this.status = customer.getStatus();
        this.statusReason = customer.getStatusReason();
        this.validFor = customer.getValidFor();
    }

    public EngagedParty getEngagedParty() {
        return engagedParty;
    }

    public void setEngagedParty(EngagedParty engagedParty) {
        this.engagedParty = engagedParty;
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
        return "FullCustomer{" +
                "href='" + href + '\'' +
                ", id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", status='" + status + '\'' +
                ", statusReason='" + statusReason + '\'' +
                ", validFor=" + validFor +
                ", account=" + accounts +
                '}';
    }
}
