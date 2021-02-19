package com.mycompany.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.mycompany.repository.Account;
import com.mycompany.repository.Customer;
import com.mycompany.repository.ValidFor;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.List;


@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class FullCustomer {



    private String href;

    private String id;

    private String name;

    private String status;

    private String statusReason;

    private ValidFor validFor;

    private List<Account> account;





    public void setCustomer(Customer customer) {
        this.href = customer.getHref();
        this.id = customer.getId();
        this.name = customer.getName();
        this.status = customer.getStatus();
        this.statusReason = customer.getStatusReason();
        this.validFor = customer.getValidFor();
    }

    public List<Account> getAccount() {
        return account;
    }

    public void setAccount(List<Account> account) {
        this.account = account;
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
                ", account=" + account +
                '}';
    }
}
