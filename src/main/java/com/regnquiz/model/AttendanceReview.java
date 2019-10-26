package com.regnquiz.model;

import org.hibernate.annotations.Immutable;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Immutable
@Table(name="vclassattendance")
@IdClass(AttendanceReviewKey.class)
public class AttendanceReview {
    @Id
    private int unitID;
    @Id
    @Temporal(TemporalType.DATE)
    private Date date;
    private int total;
    private int atten;
    private double attendPercent;

    public int getUnitID() {
        return unitID;
    }

    public Date getDate() {
        return date;
    }

    public double getAttendPercent() {
        return attendPercent;
    }

    public int getAtten() {
        return atten;
    }

    public int getTotal() {
        return total;
    }

    public void setUnitID(int unitID) {
        this.unitID = unitID;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setAtten(int atten) {
        this.atten = atten;
    }

    public void setAttendPercent(double attendPercent) {
        this.attendPercent = attendPercent;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
