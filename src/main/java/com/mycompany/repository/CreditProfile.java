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

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

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
