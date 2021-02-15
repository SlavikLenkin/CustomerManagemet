package com.mycompany.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Embeddable
@Table(name = "customer")
public class ValidForCustomer implements Serializable {

    @Column(name = "start_date_time")
    private Date  startDateTime;

    @Column(name = "end_date_time")
    private Date  endDateTime;

    public ValidForCustomer() {
    }

    public ValidForCustomer(Date startDateTime, Date endDateTime) {
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
    }

    public Date getStartDateTime() {
        return startDateTime;
    }

    public void setStartDateTime(Date startDateTime) {
        this.startDateTime = startDateTime;
    }

    public Date getEndDateTime() {
        return endDateTime;
    }

    public void setEndDateTime(Date endDateTime) {
        this.endDateTime = endDateTime;
    }
}
