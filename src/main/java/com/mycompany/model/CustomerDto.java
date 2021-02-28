package com.mycompany.model;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.mycompany.repository.*;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class CustomerDto {


    private String href;

    private String id;

    private String name;

    private String status;

    private String statusReason;

    private ValidFor validFor;

    private EngagedParty engagedParty;

    @JsonProperty(value = "account")
    private List<Account> accounts;

    @JsonProperty(value = "relatedParty")
    private List<RelatedParty> relatedParties;

    public void setCustomer(Customer customer) {
        this.href = customer.getHref();
        this.id = customer.getId();
        this.name = customer.getName();
        this.status = customer.getStatus();
        this.statusReason = customer.getStatusReason();
        this.validFor = customer.getValidFor();

    }

    public List<RelatedParty> getRelatedParties() {
        return relatedParties;
    }

    public void setRelatedParties(List<RelatedParty> relatedParties) {
        this.relatedParties = relatedParties;
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
                ", href='" + href + '\'' +
                ", id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", status='" + status + '\'' +
                ", statusReason='" + statusReason + '\'' +
                ", validFor=" + validFor +
                ", engagedParty=" + engagedParty +
                ", accounts=" + accounts +
                '}';
    }
}
