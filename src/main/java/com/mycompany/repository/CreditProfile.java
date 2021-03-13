package com.mycompany.repository;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "credit_profile")
@Data
public class CreditProfile implements Serializable {


    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "credit_profile_date")
    private Date creditProfileDate;

    @Column(name = "credit_risk_rating")
    private int creditRiskRating;

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

    public int getCreditRiskRating() {
        return creditRiskRating;
    }

    public void setCreditRiskRating(int creditRiskRating) {
        this.creditRiskRating = creditRiskRating;
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
                ", creditRiskRating=" + creditRiskRating +
                ", creditScore=" + creditScore +
                ", validFor=" + validFor +
                '}';
    }
}
