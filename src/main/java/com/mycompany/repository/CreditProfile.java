package com.mycompany.repository;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "credit_profile")
public class CreditProfile {

    @JsonIgnore
    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "credit_profile_date")
    private Date creditProfileDate;

    @Column(name = "credit_risck_raiting")
    private int creditRisckRaiting;

    @Column(name = "credit_score")
    private int creditScore;

    @Embedded
    private ValidFor validFor;

    public CreditProfile() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getCreditProfileDate() {
        return creditProfileDate;
    }

    public void setCreditProfileDate(Date creditProfileDate) {
        this.creditProfileDate = creditProfileDate;
    }

    public int getCreditRisckRaiting() {
        return creditRisckRaiting;
    }

    public void setCreditRisckRaiting(int creditRisckRaiting) {
        this.creditRisckRaiting = creditRisckRaiting;
    }

    public int getCreditScore() {
        return creditScore;
    }

    public void setCreditScore(int creditScore) {
        this.creditScore = creditScore;
    }

    public ValidFor getValidFor() {
        return validFor;
    }

    public void setValidFor(ValidFor validFor) {
        this.validFor = validFor;
    }

    @Override
    public String toString() {
        return "CreditProfile{" +
                "id='" + id + '\'' +
                ", creditProfileDate=" + creditProfileDate +
                ", creditRisckRaiting=" + creditRisckRaiting +
                ", creditScore=" + creditScore +
                ", validFor=" + validFor +
                '}';
    }
}
