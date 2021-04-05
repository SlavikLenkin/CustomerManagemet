package com.mycompany.model;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
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

    @JsonProperty(value = "agreement")
    private List<Agreement> agreements;

    @JsonProperty(value = "characteristic")
    private List<Characteristic> characteristics;

    @JsonProperty(value = "creditProfile")
    private List<CreditProfile> creditProfiles;

    @JsonProperty(value = "paymentMethod")
    private List<PaymentMethod> paymentMethods;

    @JsonProperty(value = "relatedParty")
    private List<RelatedParty> relatedParties;

    @JsonProperty(value = "contactMedium")
    private List<ContactMediumDto> contactMediumDtoList;

    public void setCustomer(Customer customer) {
        this.href = customer.getHref();
        this.id = customer.getId();
        this.name = customer.getName();
        this.status = customer.getStatus();
        this.statusReason = customer.getStatusReason();
        this.validFor = customer.getValidFor();

    }

    public List<ContactMediumDto> getContactMediumDtoList() {
        return contactMediumDtoList;
    }

    public void setContactMediumDtoList(List<ContactMediumDto> contactMediumDtoList) {
        this.contactMediumDtoList = contactMediumDtoList;
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
        return "CustomerDto{" +
                "href='" + href + '\'' +
                ", id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", status='" + status + '\'' +
                ", statusReason='" + statusReason + '\'' +
                ", validFor=" + validFor +
                ", engagedParty=" + engagedParty +
                ", accounts=" + accounts +
                ", agreements=" + agreements +
                ", characteristics=" + characteristics +
                ", creditProfiles=" + creditProfiles +
                ", paymentMethods=" + paymentMethods +
                ", relatedParties=" + relatedParties +
                ", contactMediumDtoList=" + contactMediumDtoList +
                '}';
    }
}
