package com.regnquiz.model;

import org.hibernate.annotations.Immutable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * Author: Raoul Hofmann
 * Date: 24/10/2019
 * Version: 1
 * Comment: Allows for gathering of data for attendance reviews
 */
@Entity
@Immutable // Do not write to database (due to it being a view not a table)
@Table(name="vclassattendance") // Gather data from class attendance view in database
public class AttendanceReview {
    @Id
    private int unitID;

    private Date date; // Date of the booking
    private int total; // Total number of bookings
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
